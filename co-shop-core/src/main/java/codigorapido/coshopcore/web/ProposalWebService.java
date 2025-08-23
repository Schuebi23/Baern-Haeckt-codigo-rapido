package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.ProposalsApi;
import codigorapido.coshopcore.model.Proposal;
import codigorapido.coshopcore.model.ProposalCreate;
import codigorapido.coshopcore.model.ProposalUpdate;
import codigorapido.coshopcore.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProposalWebService implements ProposalsApi {

    private final ProposalService proposalService;

    @Override
    public ResponseEntity<Proposal> createProposal(ProposalCreate proposalCreate) {
        var proposal = proposalService.createProposal(proposalCreate);
        return ResponseEntity.status(201).body(proposal);
    }

    @Override
    public ResponseEntity<Proposal> updateProposal(Long proposalId, ProposalUpdate proposalUpdate) {
        var proposal = proposalService.updateProposal(proposalId, proposalUpdate);
        return ResponseEntity.ok(proposal);
    }

    @Override
    public ResponseEntity<Void> deleteProposal(Long proposalId) {
        proposalService.deleteProposal(proposalId);
        return ResponseEntity.noContent().build();
    }

}
