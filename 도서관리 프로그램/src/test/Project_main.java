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

	int countPerson = 0; // �����ڰ� ����ΰ� ī��Ʈ
	int countBook = 0; // å�� ������ ��ΰ� ī��Ʈ
	int numgo; /// Qr�ڵ� class �� �ѱ� ����
	String book_str; // �뿩�� �� ��Ʈ������ ��Ʈ��
	int book_i; // ��Ʈ���� ��Ʈ�������� ��ȯ�ϱ����ؼ� �ӽ������ϴ� ������ �ΰ��� �Ű�����

	String category1[] = { "å ��ȣ", "å �̸�", "����", "���⿩��", "���⳯¥" };
	String category2[] = { "ȸ����ȣ", "ȸ���̸�", "��ȭ��ȣ", "������ å�� �Ǽ�" };

	String header[];
	String contents[][];

	JTable table_1;
	JTable table_2;

	JFrame frm;
	DefaultTableModel model;
	DefaultTableModel model_1;

	class QRgo implements ActionListener { // ��ư Ű ������ �г� 2�� ȣ��

		@Override
		public void actionPerformed(ActionEvent e) {

			new QRprint(numgo);
			// setVisible(false);
		}
	}

	public Project_main() {
		JFrame frm = new JFrame("�缭 ���α׷�");

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage("book_icon.png"));
		frm.setSize(989, 600);
		frm.setResizable(false);

		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(null);

		/********** ��ư ���� *************/
		JButton btn1 = new JButton("å���� QR�ڵ�");
		JButton btn2 = new JButton("�뿩");
		JButton btn3 = new JButton("�ݳ�");
		JButton btn4 = new JButton("�߰�");
		JButton btn5 = new JButton("����");
		JButton btn6 = new JButton("����");

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
		JLabel lblNewLabel = new JLabel("å ����");

		lblNewLabel.setBounds(12, 10, 473, 50);
		frm.getContentPane().add(lblNewLabel);
		lblNewLabel.setOpaque(true); // Opaque���� true�� �̸� ������ �־�� ������ ����ȴ�.
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));

		JLabel lblNewLabel_1 = new JLabel("ȸ�� ����");
		lblNewLabel_1.setOpaque(true); // Opaque���� true�� �̸� ������ �־�� ������ ����ȴ�.
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel_1.setBounds(518, 10, 453, 50);
		frm.getContentPane().add(lblNewLabel_1);
		/*****************************************************************************/

		/******************
		 * �������� ���̺�
		 **********************************************/

		JPanel panel = new JPanel();
		panel.setBounds(12, 70, 473, 450);
		frm.getContentPane().add(panel);
		model = new DefaultTableModel(category1, 0); // ����

		table_1 = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table_1);
		panel.add(scrollPane);
		/*****************************************************************************/

		/******************
		 * ȸ������ ���̺�
		 **********************************************/
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(518, 70, 453, 450);
		frm.getContentPane().add(panel_1);

		model_1 = new DefaultTableModel(category2, 0); // ����

		table_2 = new JTable(model_1);
		JScrollPane scrollPane_1 = new JScrollPane(table_2);
		panel_1.add(scrollPane_1);

		frm.setVisible(true);
		/*****************************************************************************/

		/***************************
		 * ��ư ��� ����
		 ****************************************/

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO b_auto-generated method stub

				int rowIndex_book = table_1.getSelectedRow();

				if (rowIndex_book == -1) {
					JOptionPane.showMessageDialog(null, "���ϴ� å������ ���̺��� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				numgo = rowIndex_book;
				QRgo QR = new QRgo();
				QR.actionPerformed(e);

			}

		});

		// �뿩 ��ư //
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex_book = table_1.getSelectedRow();
				int rowIndex_person = table_2.getSelectedRow();

				if (rowIndex_book == -1 || rowIndex_person == -1) {
					JOptionPane.showMessageDialog(null, "ȸ������ ���̺�� å���� ���̺��� �Ѵ� �������ּ���. ", "����",
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
						JOptionPane.showMessageDialog(null, person.get(rowIndex_person).p_name + " ȸ������  "
								+ book.get(rowIndex_book).b_name + " å�� �뿩�Ͽ����ϴ�.");
					}

				} catch (NullPointerException er) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "å�� �̹� ���Ȱų�, å�� �̹� 3�� ���Ƚ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
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

		// �ݳ���ư //
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
					JOptionPane.showMessageDialog(null, "�ݳ��Ͻ� ȸ���� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (person.get(rowIndex_person).p_book == 0) {
					JOptionPane.showMessageDialog(null, "�뿩�� ������ �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE); // ����
					// ����ó��
				}

				if (person.get(rowIndex_person).p_book >= 1) {
					Object possibilities[];

					possibilities = new Object[person.get(rowIndex_person).p_book + 1];

					for (int i = 0; i < (person.get(rowIndex_person).p_book); i++) {
						for (int j = 0; j < countBook; j++) {
							if (book.get(j).borrow_m_num == person.get(rowIndex_person).m_num) {
								System.out.println("����");
								if (pc == 0) {
									possibilities[i] = book.get(j).b_name;
									i++;
								}

							}
						}
					}
					possibilities[person.get(rowIndex_person).p_book] = "��� å �ݳ�";

					String choose1 = (String) JOptionPane.showInputDialog(null, "å�� �����ϼ���", "����",

							JOptionPane.PLAIN_MESSAGE, null, possibilities, null);

					for (int i = 0; i < countBook; i++) {
						if (book.get(i).b_name.equals(choose1)) {
							System.out.println("������");
							if (i_date2 - book.get(i).borrow_date > 3) {
								JOptionPane.showMessageDialog(null, book.get(i).b_name + " å�� "
										+ ((i_date2 - book.get(i).borrow_date) - 3) + "�� ��ü�Ǿ����ϴ�.");
								book.get(i).borrow_person = "X";
								book.get(i).borrow_date = 0;
								book.get(i).borrow_m_num = 0;
								person.get(rowIndex_person).p_book -= 1;
								break;

							} else {
								System.out.println("�Ѱ��ݳ�");
								book.get(i).borrow_person = "X";
								book.get(i).borrow_date = 0;
								book.get(i).borrow_m_num = 0;
								person.get(rowIndex_person).p_book -= 1;
								JOptionPane.showMessageDialog(null, person.get(rowIndex_person).p_name + " ȸ������ "
										+ book.get(i).b_name + " å�� �ݳ��Ͽ����ϴ�.");
								break;
							}

						} else if (choose1 == "��� å �ݳ�") {
							System.out.println("��� å �ݳ�");
							for (i = 0; i < countBook; i++) {
								if (book.get(i).borrow_m_num == person.get(rowIndex_person).m_num) {

									if (i_date2 - book.get(i).borrow_date > 3) {
										JOptionPane.showMessageDialog(null, book.get(i).b_name + " å�� "
												+ ((i_date2 - book.get(i).borrow_date) - 3) + "�� ��ü�Ǿ����ϴ�.");
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
									person.get(rowIndex_person).p_name + " ȸ������ ��� å�� �ݳ��Ͽ����ϴ�.");
							break;

						}
					}

				}

				save();
				refresh();

			}

		});

		// ȸ�� �߰� //
		btn4.addActionListener(new ActionListener() {
			int temp = 0;

			public void actionPerformed(ActionEvent e) {
				String[] rows = new String[4];
				try {
					while (true) {
						String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
						if (name.contentEquals("")) {
							JOptionPane.showMessageDialog(null, "ĭ�� ������ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
							continue;
						} else if (isNumeric(name) == true) {
							JOptionPane.showMessageDialog(null, "���ڸ� �Է��� �� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);

							break;
						}

						String P_Num = JOptionPane.showInputDialog("��ȭ��ȣ�� �Է��ϼ���");

						if (P_Num.contentEquals("")) {
							JOptionPane.showMessageDialog(null, "ĭ�� ������ϴ�.", "����", JOptionPane.ERROR_MESSAGE);

							break;
						} else if ((P_Num.length() < 11) || (P_Num.length() > 11)) {
							JOptionPane.showMessageDialog(null, "�˸��� ��ȭ��ȣ ������ �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);

							break;
						} else if (isNumeric(P_Num) == false || (Long.parseLong(P_Num) <= 0)) {
							JOptionPane.showMessageDialog(null, "�˸��� ��ȭ��ȣ ������ �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);

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
					JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ȸ���Դϴ�.", "����!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// ȸ�� ���� //

		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (true) {
					int rowIndex = table_2.getSelectedRow();

					if (rowIndex == -1) {
						JOptionPane.showMessageDialog(null, "ȸ���������� ������ ȸ���� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (person.get(rowIndex).p_book != 0) {
						JOptionPane.showMessageDialog(null, "�̹� ������ �뿩�� ȸ���� ������ �� �����ϴ�.", "����",
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
		// ȸ�� ���� //
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					while (true) {

						int rowIndex = table_2.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null, "ȸ���������� ������ ȸ���� �������ּ���.", "����",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						Object[] possibilities = { "�̸�", "��ȭ��ȣ" };

						String choose1 = (String) JOptionPane.showInputDialog(null, "�ٲ� ������ �����ϼ���.", "����",
								JOptionPane.PLAIN_MESSAGE, null, possibilities, "�̸�");
						if (choose1.equals("�̸�")) {
							String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");

							if (name.contentEquals("")) {
								JOptionPane.showMessageDialog(null, "ĭ�� ������ϴ�. �ٽ��Է��ϼ���.", "����",
										JOptionPane.ERROR_MESSAGE);
								continue;
							} else if (isNumeric(name) == true) {
								JOptionPane.showMessageDialog(null, "���ڸ� �Է��� �� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);

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

						else if (choose1.contentEquals("��ȭ��ȣ")) {
							String P_Num = JOptionPane.showInputDialog("��ȭ��ȣ�� �Է��ϼ���");
							Exception e2 = new Exception();
							for (int i = 0; i < countPerson; i++) {
								if (P_Num.equals(person.get(i).p_num)) {

									throw e2;
								}
							}

							if (P_Num.contentEquals("")) {
								JOptionPane.showMessageDialog(null, "ĭ�� ������ϴ�.", "����", JOptionPane.ERROR_MESSAGE);

								break;
							} else if ((P_Num.length() < 11) || (P_Num.length() > 11)) {
								JOptionPane.showMessageDialog(null, "�˸��� ��ȭ��ȣ ������ �ƴմϴ�.", "����",
										JOptionPane.ERROR_MESSAGE);

								break;
							} else if (isNumeric(P_Num) == false || (Long.parseLong(P_Num) <= 0)) {
								JOptionPane.showMessageDialog(null, "�˸��� ��ȭ��ȣ ������ �ƴմϴ�.", "����",
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
					JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ��ȭ��ȣ�Դϴ�.", "����!", JOptionPane.ERROR_MESSAGE);
				}

				// â�� ���������Ǿ� �߰�

				refresh();

				// ��¥ �迭�� �߰�

				save();
				// refresh();

			}
		});

		/*****************************************************************************/
	} // Test_1 endline

	class Person {
		String p_name; // ȸ�� �̸�
		String p_num; // ȸ�� ��ȭ��ȣ
		int p_book; // ȸ���� ���� å�� ����
		int m_num; // ȸ�� ��ȣ

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
		String b_number; // å ��ȣ
		String b_name; // å �̸�
		String b_aut; // å ����
		String borrow_person; // ���� ���
		int borrow_date; // ���� ��¥.
		int borrow_m_num; // ���� ȸ�� ��ȣ

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
			// ������
			// �ҷ����ų�
			// ������
			// �����Ѵ�.
			OutputStreamWriter osw = new OutputStreamWriter(fos, "MS949"); // ���ϰ��
			// ,
			// ���ڵ�
			// (��
			// ������
			// Microsoft
			// Windows��
			// �⺻
			// �ڵ�
			// ��������)
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("Book\r\n");
			for (int i = 0; i < countBook; i++) {
				bw.write(book.get(i).b_number + "\r\n"); // \r �� �ش� �� �� ������ �̵�
				// \n �����ٷ� �̵�
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