package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Project_main {

	ArrayList<Person> person = new ArrayList<Person>();
	ArrayList<String> p_name = new ArrayList<String>();
	ArrayList<String> p_num = new ArrayList<String>();
	ArrayList<Integer> p_book = new ArrayList<Integer>();
	ArrayList<Integer> m_num = new ArrayList<Integer>();

	ArrayList<Book> book = new ArrayList<Book>();
	ArrayList<String> b_number = new ArrayList<String>();
	ArrayList<String> b_name = new ArrayList<String>();
	ArrayList<String> b_aut = new ArrayList<String>();
	ArrayList<String> borrow_person = new ArrayList<String>();
	ArrayList<Integer> borrow_date = new ArrayList<Integer>();
	ArrayList<Integer> borrow_m_num = new ArrayList<Integer>();

	int countPerson = 0; // 가입자가 몇명인가 카운트
	int countBook = 0; // 책의 갯수가 몇개인가 카운트
	int numgo; /// Qr코드 class 로 넘길 변수
	String book_str; // 대여할 때 스트링형을 인트형
	int book_i; // 인트형을 스트링형으로 변환하기위해서 임시저장하는 역할의 두개의 매개변수

	String category1[] = { "책 번호", "책 이름", "저자", "대출여부", "대출날짜" };
	String category2[] = { "회원번호", "회원이름", "전화번호", "대출한 책의 권수" };

	String header[];
	String contents[][];

	JTable table_1;
	JTable table_2;

	JFrame frm;
	DefaultTableModel model;
	DefaultTableModel model_1;

	class QRgo implements ActionListener { // 버튼 키 눌리면 패널 2번 호출

		@Override
		public void actionPerformed(ActionEvent e) {

			new QRprint(numgo);
			// setVisible(false);
		}
	}

	public Project_main() {
		JFrame frm = new JFrame("사서 프로그램");

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage("book_icon.png"));
		frm.setSize(989, 600);
		frm.setResizable(false);

		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(null);

		/********** 버튼 생성 *************/
		JButton btn1 = new JButton("책정보 QR코드");
		JButton btn2 = new JButton("대여");
		JButton btn3 = new JButton("반납");
		JButton btn4 = new JButton("추가");
		JButton btn5 = new JButton("삭제");
		JButton btn6 = new JButton("수정");

		btn1.setBounds(22, 520, 120, 30);
		btn2.setBounds(190, 520, 120, 30);
		btn3.setBounds(356, 520, 120, 30);
		btn4.setBounds(518, 520, 120, 30);
		btn5.setBounds(851, 520, 120, 30);
		btn6.setBounds(685, 520, 120, 30);

		frm.getContentPane().add(btn1);
		frm.getContentPane().add(btn2);
		frm.getContentPane().add(btn3);
		frm.getContentPane().add(btn4);
		frm.getContentPane().add(btn5);
		frm.getContentPane().add(btn6);
		/*****************************************************************************/

		/*****************************************************************************/
		JLabel lblNewLabel = new JLabel("책 정보");

		lblNewLabel.setBounds(12, 10, 473, 50);
		frm.getContentPane().add(lblNewLabel);
		lblNewLabel.setOpaque(true); // Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));

		JLabel lblNewLabel_1 = new JLabel("회원 정보");
		lblNewLabel_1.setOpaque(true); // Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1.setBounds(518, 10, 453, 50);
		frm.getContentPane().add(lblNewLabel_1);
		/*****************************************************************************/

		/******************
		 * 도서정보 테이블
		 **********************************************/

		JPanel panel = new JPanel();
		panel.setBounds(12, 70, 473, 450);
		frm.getContentPane().add(panel);
		model = new DefaultTableModel(category1, 0); // 영신

		table_1 = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table_1);
		panel.add(scrollPane);
		/*****************************************************************************/

		/******************
		 * 회원정보 테이블
		 **********************************************/
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(518, 70, 453, 450);
		frm.getContentPane().add(panel_1);

		model_1 = new DefaultTableModel(category2, 0); // 영신

		table_2 = new JTable(model_1);
		JScrollPane scrollPane_1 = new JScrollPane(table_2);
		panel_1.add(scrollPane_1);

		frm.setVisible(true);
		/*****************************************************************************/

		/***************************
		 * 버튼 기능 구현
		 ****************************************/

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO b_auto-generated method stub

				int rowIndex_book = table_1.getSelectedRow();

				if (rowIndex_book == -1) {
					JOptionPane.showMessageDialog(null, "원하는 책정보를 테이블에서 선택해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					return;
				}
				numgo = rowIndex_book;
				QRgo QR = new QRgo();
				QR.actionPerformed(e);

			}

		});

		// 대여 버튼 //
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex_book = table_1.getSelectedRow();
				int rowIndex_person = table_2.getSelectedRow();

				if (rowIndex_book == -1 || rowIndex_person == -1) {
					JOptionPane.showMessageDialog(null, "회원정보 테이블과 책정보 테이블을 둘다 선택해주세요. ", "에러",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					if (!(book.get(rowIndex_book).borrow_person.equals("X"))
							|| (person.get(rowIndex_person).p_book == 3)) {
						throw null;
					} else {
						book.get(rowIndex_book).borrow_person = person.get(rowIndex_person).p_name;
						person.get(rowIndex_person).p_book += 1;
						book.get(rowIndex_book).borrow_m_num = person.get(rowIndex_person).m_num;
						JOptionPane.showMessageDialog(null, person.get(rowIndex_person).p_name + " 회원님이  "
								+ book.get(rowIndex_book).b_name + " 책을 대여하였습니다.");
					}

				} catch (NullPointerException er) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "책을 이미 빌렸거나, 책을 이미 3권 빌렸습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					// TODO: handle exception
				}

				SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
				String date = format1.format(System.currentTimeMillis());
				System.out.println(date);
				int i_date;
				i_date = Integer.parseInt(date);
				book.get(rowIndex_book).borrow_date = i_date;

				save();
				refresh();
			}

		});

		// 반납버튼 //
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pc = 0;
				SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
				String date2 = format2.format(System.currentTimeMillis());
				System.out.println(date2);
				int i_date2;
				i_date2 = Integer.parseInt(date2);

				int rowIndex_person = table_2.getSelectedRow();

				if (rowIndex_person == -1) {
					JOptionPane.showMessageDialog(null, "반납하실 회원을 선택해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (person.get(rowIndex_person).p_book == 0) {
					JOptionPane.showMessageDialog(null, "대여한 도서가 없습니다.", "에러", JOptionPane.ERROR_MESSAGE); // 진영
					// 예외처리
				}

				if (person.get(rowIndex_person).p_book >= 1) {
					Object possibilities[];

					possibilities = new Object[person.get(rowIndex_person).p_book + 1];

					for (int i = 0; i < (person.get(rowIndex_person).p_book); i++) {
						for (int j = 0; j < countBook; j++) {
							if (book.get(j).borrow_m_num == person.get(rowIndex_person).m_num) {
								System.out.println("들어갔어");
								if (pc == 0) {
									possibilities[i] = book.get(j).b_name;
									i++;
								}

							}
						}
					}
					possibilities[person.get(rowIndex_person).p_book] = "모든 책 반납";

					String choose1 = (String) JOptionPane.showInputDialog(null, "책을 선택하세요", "선택",

							JOptionPane.PLAIN_MESSAGE, null, possibilities, null);

					for (int i = 0; i < countBook; i++) {
						if (book.get(i).b_name.equals(choose1)) {
							System.out.println("용의자");
							if (i_date2 - book.get(i).borrow_date > 3) {
								JOptionPane.showMessageDialog(null, book.get(i).b_name + " 책이 "
										+ ((i_date2 - book.get(i).borrow_date) - 3) + "일 연체되었습니다.");
								book.get(i).borrow_person = "X";
								book.get(i).borrow_date = 0;
								book.get(i).borrow_m_num = 0;
								person.get(rowIndex_person).p_book -= 1;
								break;

							} else {
								System.out.println("한개반납");
								book.get(i).borrow_person = "X";
								book.get(i).borrow_date = 0;
								book.get(i).borrow_m_num = 0;
								person.get(rowIndex_person).p_book -= 1;
								JOptionPane.showMessageDialog(null, person.get(rowIndex_person).p_name + " 회원님이 "
										+ book.get(i).b_name + " 책을 반납하였습니다.");
								break;
							}

						} else if (choose1 == "모든 책 반납") {
							System.out.println("모든 책 반납");
							for (i = 0; i < countBook; i++) {
								if (book.get(i).borrow_m_num == person.get(rowIndex_person).m_num) {

									if (i_date2 - book.get(i).borrow_date > 3) {
										JOptionPane.showMessageDialog(null, book.get(i).b_name + " 책이 "
												+ ((i_date2 - book.get(i).borrow_date) - 3) + "일 연체되었습니다.");
										book.get(i).borrow_person = "X";
										book.get(i).borrow_date = 0;
										book.get(i).borrow_m_num = 0;
										person.get(rowIndex_person).p_book -= 1;
									} else {
										book.get(i).borrow_person = "X";
										book.get(i).borrow_date = 0;
										book.get(i).borrow_m_num = 0;
										person.get(rowIndex_person).p_book -= 1;
									}
								}
							}
							JOptionPane.showMessageDialog(null,
									person.get(rowIndex_person).p_name + " 회원님이 모든 책을 반납하였습니다.");
							break;

						}
					}

				}

				save();
				refresh();

			}

		});

		// 회원 추가 //
		btn4.addActionListener(new ActionListener() {
			int temp = 0;

			public void actionPerformed(ActionEvent e) {
				String[] rows = new String[4];
				try {
					while (true) {
						String name = JOptionPane.showInputDialog("이름을 입력하세요");
						if (name.contentEquals("")) {
							JOptionPane.showMessageDialog(null, "칸이 비었습니다.", "에러", JOptionPane.ERROR_MESSAGE);
							continue;
						} else if (isNumeric(name) == true) {
							JOptionPane.showMessageDialog(null, "숫자만 입력할 수 없습니다.", "에러", JOptionPane.ERROR_MESSAGE);

							break;
						}

						String P_Num = JOptionPane.showInputDialog("전화번호를 입력하세요");

						if (P_Num.contentEquals("")) {
							JOptionPane.showMessageDialog(null, "칸이 비었습니다.", "에러", JOptionPane.ERROR_MESSAGE);

							break;
						} else if ((P_Num.length() < 11) || (P_Num.length() > 11)) {
							JOptionPane.showMessageDialog(null, "알맞은 전화번호 형식이 아닙니다.", "에러", JOptionPane.ERROR_MESSAGE);

							break;
						} else if (isNumeric(P_Num) == false || (Long.parseLong(P_Num) <= 0)) {
							JOptionPane.showMessageDialog(null, "알맞은 전화번호 형식이 아닙니다.", "에러", JOptionPane.ERROR_MESSAGE);

							break;
						}

						for (int i = 0; i < countPerson; i++) {
							if (P_Num.equals(person.get(i).p_num)) {
								Exception e2 = new Exception();
								System.out.println(P_Num.length());
								throw e2;
							}
						}
						for (int i = 0; i < countPerson; i++) {
							if (person.get(i).m_num > temp)
								temp = person.get(i).m_num;
						}

						rows[0] = Integer.toString(temp + 1);
						rows[1] = name;
						rows[2] = P_Num;
						rows[3] = "0";
						model_1.addRow(rows);

						person.add(new Person(Integer.parseInt(rows[0]), name, P_Num, 0));

						countPerson++;
						save();
						break;
					}
				}

				catch (NullPointerException e1) {
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "이미 등록된 회원입니다.", "오류!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// 회원 삭제 //

		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (true) {
					int rowIndex = table_2.getSelectedRow();

					if (rowIndex == -1) {
						JOptionPane.showMessageDialog(null, "회원정보에서 삭제할 회원을 선택해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (person.get(rowIndex).p_book != 0) {
						JOptionPane.showMessageDialog(null, "이미 도서를 대여한 회원은 삭제할 수 없습니다.", "에러",
								JOptionPane.ERROR_MESSAGE);
						break;
					}

					model_1.removeRow(rowIndex);

					person.remove(rowIndex);
					countPerson--;
					for (int i = 1; i <= countPerson; i++) {
						if (person.get(i - 1).m_num != i) {
							for (int j = 0; j < countBook; j++) {
								if (book.get(j).borrow_m_num == person.get(i - 1).m_num) {
									book.get(j).borrow_m_num = i;
								}
							}
							person.get(i - 1).m_num = i;
						}
					}
					save();
					refresh();
					break;
				}
			}
		});
		// 회원 수정 //
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					while (true) {

						int rowIndex = table_2.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null, "회원정보에서 수정할 회원을 선택해주세요.", "에러",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						Object[] possibilities = { "이름", "전화번호" };

						String choose1 = (String) JOptionPane.showInputDialog(null, "바꿀 정보를 선택하세요.", "선택",
								JOptionPane.PLAIN_MESSAGE, null, possibilities, "이름");
						if (choose1.equals("이름")) {
							String name = JOptionPane.showInputDialog("이름을 입력하세요");

							if (name.contentEquals("")) {
								JOptionPane.showMessageDialog(null, "칸이 비었습니다. 다시입력하세요.", "에러",
										JOptionPane.ERROR_MESSAGE);
								continue;
							} else if (isNumeric(name) == true) {
								JOptionPane.showMessageDialog(null, "숫자만 입력할 수 없습니다.", "에러", JOptionPane.ERROR_MESSAGE);

								break;
							} else {
								for (int i = 0; i < countBook; i++) {
									if (person.get(rowIndex).p_name.equals(book.get(i).borrow_person))
										book.get(i).borrow_person = name;

								}
								person.get(rowIndex).p_name = name;
								break;
							}

						}

						else if (choose1.contentEquals("전화번호")) {
							String P_Num = JOptionPane.showInputDialog("전화번호를 입력하세요");
							Exception e2 = new Exception();
							for (int i = 0; i < countPerson; i++) {
								if (P_Num.equals(person.get(i).p_num)) {

									throw e2;
								}
							}

							if (P_Num.contentEquals("")) {
								JOptionPane.showMessageDialog(null, "칸이 비었습니다.", "에러", JOptionPane.ERROR_MESSAGE);

								break;
							} else if ((P_Num.length() < 11) || (P_Num.length() > 11)) {
								JOptionPane.showMessageDialog(null, "알맞은 전화번호 형식이 아닙니다.", "에러",
										JOptionPane.ERROR_MESSAGE);

								break;
							} else if (isNumeric(P_Num) == false || (Long.parseLong(P_Num) <= 0)) {
								JOptionPane.showMessageDialog(null, "알맞은 전화번호 형식이 아닙니다.", "에러",
										JOptionPane.ERROR_MESSAGE);

								break;
							}

							else {
								person.get(rowIndex).p_num = P_Num;
								break;
							}
						}

					}
				} catch (NullPointerException e1) {

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "이미 등록된 전화번호입니다.", "오류!", JOptionPane.ERROR_MESSAGE);
				}

				// 창에 리프레쉬되어 추가

				refresh();

				// 진짜 배열에 추가

				save();
				// refresh();

			}
		});

		/*****************************************************************************/
	} // Test_1 endline

	class Person {
		String p_name; // 회원 이름
		String p_num; // 회원 전화번호
		int p_book; // 회원이 빌린 책의 개수
		int m_num; // 회원 번호

		public Person(int m_num, String p_name, String p_num, int p_book) {
			this.m_num = m_num;
			this.p_name = p_name;
			this.p_num = p_num;
			this.p_book = p_book;

		}

		public void set_pname(String p_name) {
			this.p_name = p_name;
		}

		public String get_pname() {
			return p_name;
		}
	}

	class Book {
		String b_number; // 책 번호
		String b_name; // 책 이름
		String b_aut; // 책 저자
		String borrow_person; // 빌린 사람
		int borrow_date; // 빌린 날짜.
		int borrow_m_num; // 빌린 회원 번호

		public Book(String b_number, String b_name, String b_aut, String borrow_person, int borrow_date,
				int borrow_m_num) {
			this.b_number = b_number;
			this.b_name = b_name;
			this.b_aut = b_aut;
			this.borrow_person = borrow_person;
			this.borrow_date = borrow_date;
			this.borrow_m_num = borrow_m_num;
		}
	}

	private void refresh() {
		model = new DefaultTableModel(category1, 0);
		for (int i = 0; i < countBook; i++) {
			String[] rows = new String[6];
			rows[0] = book.get(i).b_number;
			rows[1] = book.get(i).b_name;
			rows[2] = book.get(i).b_aut;
			rows[3] = book.get(i).borrow_person;
			rows[4] = Integer.toString(book.get(i).borrow_date);
			rows[5] = Integer.toString(book.get(i).borrow_m_num);
			model.addRow(rows);
		}
		model_1 = new DefaultTableModel(category2, 0);
		for (int i = 0; i < countPerson; i++) {
			String[] rows = new String[4];
			rows[0] = Integer.toString(person.get(i).m_num);
			rows[1] = person.get(i).p_name;
			rows[2] = person.get(i).p_num;
			rows[3] = Integer.toString(person.get(i).p_book);

			model_1.addRow(rows);
		}
		table_1.setModel(model);
		table_2.setModel(model_1);
	}

	private void save() {
		try {
			FileOutputStream fos = new FileOutputStream("library.txt"); // library.txt
			// 파일을
			// 불러오거나
			// 없으면
			// 생성한다.
			OutputStreamWriter osw = new OutputStreamWriter(fos, "MS949"); // 파일경로
			// ,
			// 인코딩
			// (한
			// 국어판
			// Microsoft
			// Windows의
			// 기본
			// 코드
			// 페이지로)
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("Book\r\n");
			for (int i = 0; i < countBook; i++) {
				bw.write(book.get(i).b_number + "\r\n"); // \r 은 해당 줄 맨 앞으로 이동
				// \n 다음줄로 이동
				bw.write(book.get(i).b_name + "\r\n");
				bw.write(book.get(i).b_aut + "\r\n");
				bw.write(book.get(i).borrow_person + "\r\n");
				bw.write(book.get(i).borrow_date + "\r\n");
				bw.write(book.get(i).borrow_m_num + "\r\n");
			}
			bw.write("Person\r\n");
			for (int i = 0; i < countPerson; i++) {
				bw.write(person.get(i).m_num + "\r\n");
				bw.write(person.get(i).p_name + "\r\n");
				bw.write(person.get(i).p_num + "\r\n");
				bw.write(person.get(i).p_book + "\r\n");

			}
			bw.flush();
			osw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try {
			FileInputStream fis = new FileInputStream("library.txt");
			InputStreamReader isr = new InputStreamReader(fis, "MS949");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			if (br.readLine().trim().equals("Book")) {
				while (true) {
					temp = br.readLine().trim();
					if (!temp.equals("Person")) {
						String[] rows1 = new String[6];
						rows1[0] = temp;
						rows1[1] = br.readLine().trim();
						temp = br.readLine().trim();
						rows1[2] = temp;
						temp = br.readLine().trim();

						if (temp.contentEquals("0"))
							rows1[3] = "0";
						else
							rows1[3] = temp;
						rows1[4] = br.readLine().trim();
						rows1[5] = br.readLine().trim();

						model.addRow(rows1);
						book.add(new Book(rows1[0], rows1[1], rows1[2], rows1[3], Integer.parseInt(rows1[4]),
								Integer.parseInt(rows1[5])));
						countBook++;
					} else {
						while (true) {
							String[] rows2 = new String[4];
							rows2[0] = br.readLine().trim();
							rows2[1] = br.readLine().trim();
							rows2[2] = br.readLine().trim();
							rows2[3] = br.readLine().trim();

							model_1.addRow(rows2);
							person.add(new Person(Integer.parseInt(rows2[0]), rows2[1], rows2[2],
									Integer.parseInt(rows2[3])));
							countPerson++;
						}
					}
				}
			}
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			return;
		}
	}

	public boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		Project_main t = new Project_main();
		t.load();
	}
}