package codigorapido.coshopcore.service;

import codigorapido.coshopcore.model.Commit;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.model.Member;
import codigorapido.coshopcore.model.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final ItemService itemService;
    private final GroupService groupService;

    public List<String> computeGroupSettlement(Long groupId) {
        var items = itemService.listGroupItems(groupId);
        var members = groupService.listGroupMembers(groupId);

        var balances = settle(items);
        return computeTransactions(balances, members);
    }


    public Map<Long, Double> settle(List<Item> items) {

        Map<Long, Double> overallBalances = new HashMap<>();

        items.forEach(item -> {
            // Member contributions in CHF
            Map<Long, Double> memberContributions = item.getCommits().stream()
                .collect(Collectors.groupingBy(Commit::getMemberId,
                        Collectors.summingDouble(Commit::getPrice)));

            // Member requests in quantity
            Map<Long, Double> memberRequests = item.getRequests().stream()
                .filter(request -> !request.getForEveryone())
                .collect(Collectors.groupingBy(Request::getMemberId,
                        Collectors.summingDouble(Request::getQtyRequested)));

            // Quantity requested for everyone
            var forEveryoneRequests = item.getRequests().stream()
                .filter(Request::getForEveryone)
                .mapToDouble(Request::getQtyRequested)
                .sum();

            Map<Long, Double> balancesForItem = new HashMap<>();

            var priceForItem = item.getCommits().stream().mapToDouble(Commit::getPrice).sum();
            double totalRequests = item.getRequests().stream().mapToDouble(Request::getQtyRequested).sum();
            double pricePerUnit = priceForItem / totalRequests;

            Set<Long> allMembers = new HashSet<>();
            allMembers.addAll(memberContributions.keySet());
            allMembers.addAll(memberRequests.keySet());

            double sharedPerMember = forEveryoneRequests / allMembers.size();

            for (Long memberId : allMembers) {
                double contributed = memberContributions.getOrDefault(memberId, 0.0);
                double requestedQty = memberRequests.getOrDefault(memberId, 0.0) + sharedPerMember;
                double consumedValue = requestedQty * pricePerUnit;

                double balance = contributed - consumedValue;
                balancesForItem.put(memberId, balance);
            }

            balancesForItem.forEach((memberId, balance) ->
                overallBalances.merge(memberId, balance, Double::sum)
            );
        });

        return overallBalances;
    }

    public List<String> computeTransactions(Map<Long, Double> balances, List<Member> members) {
        List<String> transactions = new ArrayList<>();

        // Positive (Gläubiger) und Negative (Schuldner) trennen
        List<Map.Entry<Long, Double>> creditors = balances.entrySet().stream()
            .filter(e -> e.getValue() > 0)
            .toList();

        List<Map.Entry<Long, Double>> debtors = balances.entrySet().stream()
            .filter(e -> e.getValue() < 0)
            .toList();

        int i = 0, j = 0;
        while (i < creditors.size() && j < debtors.size()) {
            var creditor = creditors.get(i);
            var debtor = debtors.get(j);

            double amount = Math.min(creditor.getValue(), -debtor.getValue());

            String creditorName = members.stream().filter(m -> m.getId().equals(creditor.getKey()))
                .findFirst().map(Member::getDisplayName).orElse("Unknown");
            String debtorName = members.stream().filter(m -> m.getId().equals(debtor.getKey()))
                .findFirst().map(Member::getDisplayName).orElse("Unknown");

            transactions.add(debtorName + " zahlt " + amount + " CHF an " + creditorName);

            // Update balances
            creditor.setValue(creditor.getValue() - amount);
            debtor.setValue(debtor.getValue() + amount);

            if (creditor.getValue() == 0) i++;
            if (debtor.getValue() == 0) j++;
        }

        return transactions;
    }
}
