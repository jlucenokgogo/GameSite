package game.example.server.repositories;

import game.example.server.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ReplyRepository")
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Boolean existsReplyByIdReply(Long idReply);
    List<Reply> findRepliesByActiveReply(Boolean activeReply);

}