package S1_Member;

import java.util.Arrays;

import S_MyUtil.Util;

public class MemberBoard {

	static public MemberBoard instance = new MemberBoard();

	static public MemberBoard getInstance() {
		return instance;
	}

	String[][] board = null;
	int count = 0; // ��ü �Խñ� ��
	int pageSize = 3; // �� �������� ������ �Խñ� ��
	int curPageNum = 1; // ���� ������ ��ȣ
	int pageCount = 0; // ��ü ������ ����
	int startRow = 0; // ���� �������� �Խñ� ���� ��ȣ
	int endRow = 0; // ���� �������� �Խñ� ������ ��ȣ
	String ID;

	// System.out.print("���������� ������ �Խñ� ���� => ");
	// pageSize = Util.scan.nextInt();

	public void printBoard(String id) {
		System.out.println("��ü �Խñ� ���� => " + count);
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
			System.out.printf("(�ε��� %2d ~ %2d)\n", startRow, endRow);
			System.out.println("��ȣ\t����\t\t�ۼ���");
			if (count > 0) {

				for (int i = startRow; i < endRow + 1; i++) {
					System.out.print("(" + i + ") ");
					System.out.printf("\t%s\t\t%s \n", board[i - 1][0], board[i - 1][2]);
				}
			}

			// ������ �̵�
			if (curPageNum != 1) {
				System.out.print("[���� 1]");
			} else {
				System.out.print("     ");
			}
			System.out.printf(" page(%d/%d) ", curPageNum, pageCount);
			if (curPageNum != pageCount) {
				System.out.print("[���� 2]");
			} else {
				System.out.print("     ");
			}
			System.out.println();
			System.out.println("[�۾��� 3] [���� 4] [�ۺ��� 5] [���� 0]");
			System.out.println("���� >> ");
			int choice = Util.scan.nextInt();

			if (choice == 1) {
				if (curPageNum == 1) {
					System.err.println("���������� ����");
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
					System.err.println("���������� ����");
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
				System.out.print("���� : ");
				String title = Util.scan.nextLine();
				b[count][0] = title;

				System.out.println("���� : (0�� �Է½� ����)");
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
				System.out.print("������ �Խñ� ��ȣ�� �Է��ϼ��� : ");
				int del = Util.scan.nextInt() - 1;

				if (del < 0 || del > count) {
					System.err.println("�Խñ� ��ȣ ����");
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
				System.out.print("�Խñ� ��ȣ �Է� : ");
				int num = Util.scan.nextInt();

				if (num < startRow || num > endRow) {
					System.err.println("���� ���������� ���̴� ��ȣ�� �Է��ϼ���");
					continue;
				}
				System.out.println("�ۼ��� : " + board[startRow - 1][2]);
				System.out.println("���� : " + board[startRow - 1][0]);
				System.out.println("���� : " + board[startRow - 1][1]);
				System.out.println();
			} else if (choice == 0) {
				System.err.println("�Խ��� ����");
				break;
			} else {
				System.err.println("[�޼���] �Է� ����");
			}

//			for (int i = 0; i < board.length; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
		}
	}

}
