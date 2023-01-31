package S1_Member;

import java.util.Arrays;

import S_MyUtil.Util;

public class MemberBoard {

	static public MemberBoard instance = new MemberBoard();

	static public MemberBoard getInstance() {
		return instance;
	}

	String[][] board = null;
	int count = 0; // 전체 게시글 수
	int pageSize = 3; // 한 페이지에 보여줄 게시글 수
	int curPageNum = 1; // 현재 페이지 번호
	int pageCount = 0; // 전체 페이지 개수
	int startRow = 0; // 현재 페이지의 게시글 시작 번호
	int endRow = 0; // 현재 페이지의 게시글 마지막 번호
	String ID;

	// System.out.print("한페이지에 보여줄 게시글 숫자 => ");
	// pageSize = Util.scan.nextInt();

	public void printBoard(String id) {
		System.out.println("전체 게시글 숫자 => " + count);
		ID = id;

		while (true) {
			pageCount = (count / pageSize) + 1;

			if (count % pageSize == 0 && count != 0) {
				pageCount--;
			}
			if (count > 0 && pageCount == 1) {
				startRow = 1;
				if (pageSize >= count) {
					endRow = count;
				} else if (pageSize < count) {
					endRow = startRow + pageSize + 1;
				}
			}
			if (curPageNum > pageCount) {
				curPageNum = pageCount;
				endRow = count;
				startRow = endRow - pageSize - 1;
			}
			System.out.printf("(인덱스 %2d ~ %2d)\n", startRow, endRow);
			System.out.println("번호\t제목\t\t작성자");
			if (count > 0) {

				for (int i = startRow; i < endRow + 1; i++) {
					System.out.print("(" + i + ") ");
					System.out.printf("\t%s\t\t%s \n", board[i - 1][0], board[i - 1][2]);
				}
			}

			// 페이지 이동
			if (curPageNum != 1) {
				System.out.print("[이전 1]");
			} else {
				System.out.print("     ");
			}
			System.out.printf(" page(%d/%d) ", curPageNum, pageCount);
			if (curPageNum != pageCount) {
				System.out.print("[이후 2]");
			} else {
				System.out.print("     ");
			}
			System.out.println();
			System.out.println("[글쓰기 3] [삭제 4] [글보기 5] [종료 0]");
			System.out.println("선택 >> ");
			int choice = Util.scan.nextInt();

			if (choice == 1) {
				if (curPageNum == 1) {
					System.err.println("이전페이지 없음");
					continue;
				}
				startRow = startRow - pageSize;
				if (startRow < 1) {
					startRow = 1;
				}
				endRow = startRow + pageSize - 1;
				curPageNum--;

			} else if (choice == 2) {
				if (curPageNum == pageCount) {
					System.err.println("다음페이지 없음");
					continue;
				}
				startRow = endRow + 1;
				endRow = endRow + pageSize;
				if (endRow > count) {
					endRow = count;
				}
				curPageNum++;

			} else if (choice == 3) {
				String[][] b = new String[count + 1][3];

				Util.scan.nextLine();
				System.out.print("제목 : ");
				String title = Util.scan.nextLine();
				b[count][0] = title;

				System.out.println("내용 : (0만 입력시 종료)");
				String t = "";
				while (true) {
					String text = Util.scan.nextLine();
					if (text.equals("0")) {
						break;
					}
					t += text;
				}
				b[count][1] = t;
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < 2; j++) {
						b[i][j] = board[i][j];
					}
				}
				b[count][2] = ID;
				count++;
				board = new String[count][3];
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < 3; j++) {
						board[i][j] = b[i][j];
					}
				}

			} else if (choice == 4) {
				String[][] b = new String[count - 1][2];
				System.out.print("삭제할 게시글 번호를 입력하세요 : ");
				int del = Util.scan.nextInt() - 1;

				if (del < 0 || del > count) {
					System.err.println("게시글 번호 오류");
					continue;
				}
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < 2; j++) {
						if (i < del) {
							b[i][j] = board[i][j];
						} else if (i > del) {
							b[i - 1][j] = board[i][j];
						}

					}
				}
				count--;
				board = new String[count][3];
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < 3; j++) {
						board[i][j] = b[i][j];
					}
				}
			} else if (choice == 5) {
				System.out.print("게시글 번호 입력 : ");
				int num = Util.scan.nextInt();

				if (num < startRow || num > endRow) {
					System.err.println("현재 페이지에서 보이는 번호를 입력하세요");
					continue;
				}
				System.out.println("작성자 : " + board[startRow - 1][2]);
				System.out.println("제목 : " + board[startRow - 1][0]);
				System.out.println("내용 : " + board[startRow - 1][1]);
				System.out.println();
			} else if (choice == 0) {
				System.err.println("게시판 종료");
				break;
			} else {
				System.err.println("[메세지] 입력 오류");
			}

//			for (int i = 0; i < board.length; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
		}
	}

}
