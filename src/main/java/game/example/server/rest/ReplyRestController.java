package game.example.server.rest;

import game.example.server.dto.ReplyDTO;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.service.abstartion.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("/api/replies")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ReplyRestController {

    private final ReplyService replyService;

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllReplyByStatus")
    @Async("controllerThreadPool")
    @ResponseBody
    public CompletableFuture<ArrayList<ReplyDTO>> getAllReplyByStatus() {
        return replyService.getAllReplyByStatus(Boolean.TRUE);
    }

   // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllReplies")
    @Async("controllerThreadPool")
    @ResponseBody
    public CompletableFuture<ArrayList<ReplyDTO>> getAllReplies() {
        return replyService.getAllDto();
    }

    @GetMapping(value = "/getReplyById/{id}")
    public ResponseEntity<ReplyDTO> getReplyById(@PathVariable Long id)
            throws ModelNoFound {
        return new ResponseEntity<>(replyService.readOneModel(id), HttpStatus.OK);
    }

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping(value = "/saveNewReply")
    public ResponseEntity<ReplyDTO> saveNewReply(@RequestBody ReplyDTO replyDTO)
            throws ModelNoFound, ModelExist {
        return new ResponseEntity<>(replyService.save(replyDTO), HttpStatus.CREATED);
    }

    // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/acceptReply")
    public ResponseEntity<ReplyDTO> acceptReply(@RequestBody ReplyDTO replyDTO)
            throws ModelNoFound {
        return new ResponseEntity<>(replyService.update(replyDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteReplyById/{id}")
    public ResponseEntity<HttpStatus> deleteReplyById(@PathVariable Long id)
            throws ModelNoFound {
        replyService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
