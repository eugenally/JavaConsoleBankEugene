package banking.threeby3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// 1. 3x3 크기의 2차원 배열 선언
        int[][] array = new int[3][3];
        
        // 2. 1부터 9까지의 숫자를 저장할 리스트 생성
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }

        // 3. 리스트의 숫자를 무작위로 섞기 (중복 없이 섞임)
        Collections.shuffle(numbers);

        // 4. 중첩 반복문(for)을 돌며 임의의 숫자 채우기
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // Math.random()은 0.0 이상 1.0 미만의 실수를 반환합니다.
                // 아래 식은 1부터 10 사이의 임의의 정수를 생성합니다.
                array[i][j] = (int) (Math.random() * 10) + 1;
            }
        }

        // 5. 배열에 담긴 값 출력하기
        System.out.println("--- 생성된 3x3 랜덤 배열 ---");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // 숫자가 예쁘게 정렬되도록 탭(\t) 문자를 추가했습니다.
                System.out.print(array[i][j] + "\t");
            }
            System.out.println(); // 한 행이 끝나면 줄바꿈
        }
    }
}