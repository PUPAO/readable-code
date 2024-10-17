package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CellPositionTest {

    @DisplayName("객체 생성 성공")
    @Test
    void successOf() {
        System.out.println("CellPositionTest.successOf");

        // given & when
        CellPosition position = CellPosition.of(3, 4);

        // then
        assertThat(position.getRowIndex()).isEqualTo(3);
        assertThat(position.getColIndex()).isEqualTo(4);
    }

    @DisplayName("좌표 이탈로 생성 실패")
    @Test
    void failedOf() {
        System.out.println("CellPositionTest.failedOf");

        // given & when
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                CellPosition.of(-1, 4);
            },
            "객체가 제대로 생성 되었습니다."
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("올바르지 않은 좌표입니다.");
    }
}
