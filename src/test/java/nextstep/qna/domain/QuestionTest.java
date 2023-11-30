package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 삭제하는 경우 삭제 상태가 변경된다.")
    void deleteQuestionTest() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 시 질문 작성자와 다른 유저인 경우 exception throw")
    void deleteUserNotWriterExceptionTest() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("질문 삭제 시 질문 작성자와 같은 경우 삭제한다.")
    void deleteSameAsUserAndWriterTest() {
        assertDoesNotThrow(() -> Q1.delete(NsUserTest.JAVAJIGI));
        assertThat(Q1.isDeleted()).isTrue();
    }

}
