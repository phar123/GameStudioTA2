package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceFile;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {


        private CommentService commentService = new CommentServiceFile();

        @Test
        public void testReset() {
            commentService.addComment(new Comment("sk/tuke/gamestudio","Jano","Koment1",new Date()));
            commentService.reset();
            assertEquals(0, commentService.getComments("sk/tuke/gamestudio").size());

        }
        @Test
        public void testAddComment(){
            commentService.reset();
            var date = new Date();

            commentService.addComment(new Comment("sk/tuke/gamestudio","Jano","Komentar dalsi",date));
            var comments = commentService.getComments("sk/tuke/gamestudio");
            assertEquals(1, comments.size());
            assertEquals("sk/tuke/gamestudio", comments.get(0).getGame());
            assertEquals("Jano", comments.get(0).getUsername());
            assertEquals("Komentar dalsi", comments.get(0).getComment());
            assertEquals(date, comments.get(0).getCommented_on());

        }

}
