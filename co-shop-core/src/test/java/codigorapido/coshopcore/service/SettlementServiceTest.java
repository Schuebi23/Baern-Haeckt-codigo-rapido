package codigorapido.coshopcore.service;

import static org.junit.jupiter.api.Assertions.*;

import codigorapido.coshopcore.model.Commit;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.model.Member;
import codigorapido.coshopcore.model.Request;
import java.util.List;
import org.junit.jupiter.api.Test;

class SettlementServiceTest {


    private final SettlementService settlementService = new SettlementService(null, null);

    @Test
    void testSettle() {

        var balances = settlementService.settle(createItems());
        assertNotNull(balances);
        assertEquals(2, balances.size());
        assertTrue(balances.containsKey(1L)); // Alice
        assertTrue(balances.containsKey(2L)); // Bob
        assertEquals(15.0, balances.get(1L), 0.01); // Alice should receive 10€
        assertEquals(-15.0, balances.get(2L), 0.01); // Bob should pay 10€

        var transactions = settlementService.computeTransactions(balances, createMembers());
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        var transaction = transactions.get(0);
        assertEquals("Bob zahlt 15.0 CHF an Alice", transaction);
    }

    public static List<Member> createMembers() {
        return List.of(
            new Member(1L, "Alice"),
            new Member(2L, "Bob")
        );
    }

    public static List<Item> createItems() {
        Item apples = new Item();
        apples.setId(1L);
        apples.setGroupId(100L);
        apples.setName("Apples");
        apples.setUnit("kg");
        apples.setDescription("Fresh red apples");

        // Requests
        Request req1 = new Request();
        req1.setId(1L);
        req1.setMemberId(1L); // Alice
        req1.setItemId(1L);
        req1.setQtyRequested(2L); // Alice wants 2kg
        req1.setForEveryone(false);

        Request req2 = new Request();
        req2.setId(2L);
        req2.setMemberId(2L); // Bob
        req2.setItemId(1L);
        req2.setQtyRequested(3L); // Bob wants 3kg
        req2.setForEveryone(false);

        Request req3 = new Request();
        req3.setId(3L);
        req3.setMemberId(1L); // Doesn’t matter who enters it
        req3.setItemId(1L);
        req3.setQtyRequested(5L); // 5kg for everyone
        req3.setForEveryone(true);

        apples.getRequests().addAll(List.of(req1, req2, req3));

        // Commits (who paid what)
        Commit commit1 = new Commit();
        commit1.setId(1L);
        commit1.setMemberId(1L); // Alice paid 60€
        commit1.setItemId(1L);
        commit1.setQtyCommitted(5);
        commit1.setPrice(60f);
        commit1.setCommited(true);

        Commit commit2 = new Commit();
        commit2.setId(2L);
        commit2.setMemberId(2L); // Bob paid 40€
        commit2.setItemId(1L);
        commit2.setQtyCommitted(5);
        commit2.setPrice(40f);
        commit2.setCommited(true);

        apples.getCommits().addAll(List.of(commit1, commit2));

        return List.of(apples);
    }

}
