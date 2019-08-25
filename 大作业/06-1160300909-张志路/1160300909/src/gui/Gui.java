package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import codeGen.CodeGen;
import grammar.SyntaxParser;
import grammar.Tree;
import lexical.Lexical;
import semantic.FourAddr;
import semantic.Symbol;
import semantic.Smantic;
import semantic.util;

import optimization.Optimization;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import javax.swing.UIManager;

public class Gui extends JFrame
{

	private static final long serialVersionUID=1L;
	private static String file_name = ""; //= "test1.txt";
	//private JTable table;

	public void Gui_1() 
	{		
		JFrame JFrame = new JFrame();

		JFrame.setForeground(Color.WHITE);
		JFrame.setFont(new Font("����", Font.BOLD, 25));
		
		JFrame.setTitle(" �� �� �� �� �� ��");    //������ʾ���ڱ���
		JFrame.setSize(875,868);    //���ô�����ʾ�ߴ�
		JFrame.setDefaultCloseOperation(2);    //�ô����Ƿ���Թر�
		JFrame.getContentPane().setLayout(null);

			
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setToolTipText("");
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(15, 15, 502, 466);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JFrame.getContentPane().add(scrollPane2);
		
		String[] name1 = new String[] {"\u884C\u53F7","Token", "\u7C7B\u522B", "\u79CD\u522B\u7801"};
        JTable table1 = new JTable(new DefaultTableModel(new Object[][] {}, name1));
        table1.setForeground(Color.BLACK);
        table1.setFillsViewportHeight(true);
        table1.setFont(new Font("����", Font.BOLD, 18));
		table1.setBackground(new Color(255, 255, 255));
		scrollPane2.setViewportView(table1);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane3.setBackground(SystemColor.menu);
		scrollPane3.setBounds(15, 496, 502, 288);
		JFrame.getContentPane().add(scrollPane3);
		
		String[] name2 = new String[] {"�����к�", "Token", "��ϸ˵��"};
		JTable table2 = new JTable(new DefaultTableModel(new Object[][] {}, name2));
		table2.setForeground(Color.RED);
		table2.setFont(new Font("����", Font.BOLD, 18));
		table2.setFillsViewportHeight(true);
		table2.setBackground(Color.WHITE);
		//table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane3.setViewportView(table2);
	
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane4.setBackground(SystemColor.menu);
		scrollPane4.setBounds(547, 15, 291, 466);
		JFrame.getContentPane().add(scrollPane4);
		
		String[] name3 = new String[] {"��ʶ��", "λ��"};
		JTable table3 = new JTable(new DefaultTableModel(new Object[][] {}, name3));
		table3.setForeground(Color.BLACK);
		table3.setFont(new Font("����", Font.BOLD, 18));
		table3.setFillsViewportHeight(true);
		table3.setBackground(Color.WHITE);
		scrollPane4.setViewportView(table3);
		
		JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane5.setBackground(SystemColor.menu);
		scrollPane5.setBounds(547, 496, 291, 289);
		JFrame.getContentPane().add(scrollPane5);
		
		String[] name4 = new String[] {"����", "λ��"};
		JTable table4 = new JTable(new DefaultTableModel(new Object[][] {}, name4));
		table4.setForeground(Color.BLACK);
		table4.setFont(new Font("����", Font.BOLD, 18));
		table4.setFillsViewportHeight(true);
		table4.setBackground(Color.WHITE);
		scrollPane5.setViewportView(table4);

		DefaultTableModel model1 = new DefaultTableModel(new Object[][]{},name1);
		table1.setModel(model1);
		
		DefaultTableModel model2 = new DefaultTableModel(new Object[][]{},name2);
		table2.setModel(model2);
	
		DefaultTableModel model3 = new DefaultTableModel(new Object[][]{},name3);
		table3.setModel(model3);

		DefaultTableModel model4 = new DefaultTableModel(new Object[][]{},name4);
		table4.setModel(model4);

		Lexical text_lex = new Lexical(file_name, table1, table2, table3, table4);
		text_lex.analyze();

		JFrame.setVisible(true);    //���ô����Ƿ�ɼ�
	}
	
	public void Gui_2() 
	{
		JFrame JFrame = new JFrame();

		JFrame.setForeground(Color.WHITE);
		JFrame.setFont(new Font("����", Font.BOLD, 25));
		
		JFrame.setTitle(" �� �� �� �� �� ��");    //������ʾ���ڱ���
		JFrame.setSize(1084,868);    //���ô�����ʾ�ߴ�
		JFrame.setDefaultCloseOperation(2);    //�ô����Ƿ���Թر�
		JFrame.getContentPane().setLayout(null);
		
		// �����Ի�����������, ������ڿ��Ը����Լ�����Ҫ����κ�������������ǲ���
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setToolTipText("");
		scrollPane1.setBackground(SystemColor.menu);
		scrollPane1.setBounds(15, 15, 489, 316);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JFrame.getContentPane().add(scrollPane1);
		
		String[] name1 = new String[] {"����ʽ"};
		JTable table1 = new JTable(new DefaultTableModel(new Object[][] {}, name1));
		table1.setForeground(Color.BLACK);
		table1.setFont(new Font("����", Font.BOLD, 15));
		table1.setFillsViewportHeight(true);
		table1.setBackground(Color.WHITE);
		scrollPane1.setViewportView(table1);		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(15, 369, 1010, 428);
		JFrame.getContentPane().add(scrollPane2);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		scrollPane2.setViewportView(textArea2);
		
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane3.setBackground(SystemColor.menu);
		scrollPane3.setBounds(539, 15, 489, 316);
		JFrame.getContentPane().add(scrollPane3);
		
		String[] name3 = new String[] {"���󱨸�"};
		JTable table3 = new JTable(new DefaultTableModel(new Object[][] {}, name3));
		table3.setForeground(Color.RED);
		table3.setFont(new Font("����", Font.BOLD, 15));
		table3.setFillsViewportHeight(true);
		table3.setBackground(Color.WHITE);
		scrollPane3.setViewportView(table3);
		
		List<String> result2 = new ArrayList<String>(); 
		List<String> errors = new ArrayList<String>(); 

		SyntaxParser se = new SyntaxParser(file_name,result2,errors);
		

		String file_name2 = "LR_Analysis_Table.txt";
		// ��ȡ�ļ���д��JTextArea����
		File file = new File(file_name2);
		try
		{
			textArea2.setText("");;
			InputStream in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte=in.read()) != -1) 
			{
				textArea2.append(""+(char)tempbyte);
			}
			in.close();
		}
		catch(Exception event)
		{
			event.printStackTrace();
		}

		DefaultTableModel model1 = new DefaultTableModel(gui_table(result2),name1);
		table1.setModel(model1);
	
		DefaultTableModel model3 = new DefaultTableModel(gui_table(errors),name3);
		table3.setModel(model3);
		
		JLabel lblLr = new JLabel("L R ( 1 ) \u5206 \u6790 \u8868");
		lblLr.setFont(new Font("����", Font.BOLD, 15));
		lblLr.setHorizontalAlignment(SwingConstants.CENTER);
		lblLr.setBounds(15, 346, 1072, 21);
		JFrame.getContentPane().add(lblLr);
		
		JFrame.setVisible(true);
	}

	
	public static Object[][] gui_table(List<String> e)
	{
		int le = e.size();
		Object[][] t = new Object[le][1];
		for (int i=0; i<le; i++)
		{
			t[i][0] = e.get(i);
		}	
		return t;		
	}

	
	public void Gui_3()
	{
		JFrame JFrame = new JFrame();

		JFrame.setForeground(Color.WHITE);
		JFrame.setFont(new Font("����", Font.BOLD, 25));
		
		JFrame.setTitle(" �� �� �� �� �� ��");    //������ʾ���ڱ���
		JFrame.setSize(1017,868);    //���ô�����ʾ�ߴ�
		JFrame.setDefaultCloseOperation(2);    //�ô����Ƿ���Թر�
		JFrame.getContentPane().setLayout(null);
		

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setToolTipText("");
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(15, 25, 965, 466);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JFrame.getContentPane().add(scrollPane2);
		
		String[] name1 = new String[] {"���","��Ԫʽ", "����ַ"};
        JTable table1 = new JTable(new DefaultTableModel(new Object[][] {}, name1));
        table1.setForeground(Color.BLACK);
        table1.setFillsViewportHeight(true);
        table1.setFont(new Font("����", Font.BOLD, 15));
		table1.setBackground(new Color(255, 255, 255));
		scrollPane2.setViewportView(table1);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane3.setBackground(SystemColor.menu);
		scrollPane3.setBounds(479, 513, 501, 288);
		JFrame.getContentPane().add(scrollPane3);
		
		String[] name2 = new String[] {"���", "����", "����", "offset"};
		JTable table2 = new JTable(new DefaultTableModel(new Object[][] {}, name2));
		table2.setForeground(Color.BLACK);
		table2.setFont(new Font("����", Font.BOLD, 15));
		table2.setFillsViewportHeight(true);
		table2.setBackground(Color.WHITE);
		//table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane3.setViewportView(table2);
		
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane4.setBackground(SystemColor.menu);
		scrollPane4.setBounds(15, 512, 449, 289);
		JFrame.getContentPane().add(scrollPane4);
		
		String[] name3 = new String[] {"���󱨸�"};
		JTable table3 = new JTable(new DefaultTableModel(new Object[][] {}, name3));
		table3.setForeground(Color.RED);
		table3.setFont(new Font("����", Font.BOLD, 15));
		table3.setFillsViewportHeight(true);
		table3.setBackground(Color.WHITE);
		scrollPane4.setViewportView(table3);

		List<Stack<Symbol>> table = new ArrayList<Stack<Symbol>>();  // ���ű�  
		List<String> three_addr = new ArrayList<String>();  // ����ַָ������
		List<FourAddr> four_addr = new ArrayList<FourAddr>();  // ����ַָ������
		List<String> errors = new ArrayList<String>();  // ��������
		
		Smantic se = new Smantic(file_name,table,three_addr,four_addr,errors);
		
		Object[][] gui_ins = util.gui_ins(three_addr,four_addr);
		Object[][] gui_table = util.gui_table(table);
		Object[][] gui_errors = util.gui_errors(errors);
		
		DefaultTableModel model1 = new DefaultTableModel(gui_ins,name1);
		table1.setModel(model1);
		
		DefaultTableModel model2 = new DefaultTableModel(gui_table,name2);
		table2.setModel(model2);
	
		DefaultTableModel model3 = new DefaultTableModel(gui_errors,name3);
		table3.setModel(model3);

		JFrame.setVisible(true);    //���ô����Ƿ�ɼ�
	}

	
	public void Gui_4()
	{
		JFrame JFrame = new JFrame();
	
		JFrame.setForeground(Color.WHITE);
		JFrame.setFont(new Font("����", Font.BOLD, 25));
		
		JFrame.setTitle("�����Ż�");    //������ʾ���ڱ���
		JFrame.setSize(650,583);    //���ô�����ʾ�ߴ�
		JFrame.setDefaultCloseOperation(2);    //�ô����Ƿ���Թر�
		JFrame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(15, 15, 580, 497);
		JFrame.getContentPane().add(scrollPane2);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		scrollPane2.setViewportView(textArea2);
		
		/*List<FourAddr> four_addr = new ArrayList<FourAddr>();  // ����ַָ������
		Smantic se = new Smantic(file_name,four_addr);
		textArea2.setText(Optimization.basicBlock(four_addr));*/

		Optimization op = new Optimization(file_name);
		textArea2.setText(op.getBlock());
		
		JFrame.setVisible(true);    //���ô����Ƿ�ɼ�
	}
	
	public void Gui_5()
	{
		JFrame JFrame = new JFrame();
	
		JFrame.setForeground(Color.WHITE);
		JFrame.setFont(new Font("����", Font.BOLD, 25));
		
		JFrame.setTitle("Ŀ�����");    //������ʾ���ڱ���
		JFrame.setSize(447,583);    //���ô�����ʾ�ߴ�
		JFrame.setDefaultCloseOperation(2);    //�ô����Ƿ���Թر�
		JFrame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(15, 15, 395, 497);
		JFrame.getContentPane().add(scrollPane2);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		scrollPane2.setViewportView(textArea2);
		
		/*List<FourAddr> four_addr = new ArrayList<FourAddr>();  // ����ַָ������
		Smantic se = new Smantic(file_name,four_addr);
		textArea2.setText(CodeGen.asm(four_addr));*/
		
		CodeGen co = new CodeGen(file_name);
		textArea2.setText(co.getss());
		
		JFrame.setVisible(true);    //���ô����Ƿ�ɼ�
	}
	
	public Gui()
	{
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("����", Font.BOLD, 25));
		
		setTitle(" �� �� �� ��");    //������ʾ���ڱ���
		setSize(748,626);    //���ô�����ʾ�ߴ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�ô����Ƿ���Թر�
		getContentPane().setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 18, 527, 476);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 17));
		scrollPane1.setViewportView(textArea);
		scrollPane1.setRowHeaderView(new LineNumber());

		JButton button1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		button1.setBackground(UIManager.getColor("Button.highlight"));
		button1.setForeground(Color.BLACK);
		
		button1.setFont(new Font("����", Font.BOLD, 23));
		button1.setBounds(10, 509, 335, 46);
		getContentPane().add(button1);
		
		JButton button2 = new JButton("\u6E05\u7A7A\u6587\u672C");
		button2.setBackground(UIManager.getColor("Button.highlight"));
		button2.setForeground(Color.BLACK);
		
		button2.setFont(new Font("����", Font.BOLD, 23));
		button2.setBounds(376, 509, 335, 47);
		getContentPane().add(button2);
		
		
		JButton button3_1 = new JButton("\u8BCD\u6CD5\u5206\u6790");
		button3_1.setFont(new Font("����", Font.BOLD, 23));
		button3_1.setBounds(552, 15, 159, 71);
		getContentPane().add(button3_1);
		
		JButton button3_2 = new JButton("\u8BED\u6CD5\u5206\u6790");
		button3_2.setFont(new Font("����", Font.BOLD, 23));
		button3_2.setBounds(552, 117, 159, 75);
		getContentPane().add(button3_2);
		
		JButton button3_3 = new JButton("\u8BED\u4E49\u5206\u6790");
		button3_3.setFont(new Font("����", Font.BOLD, 23));
		button3_3.setBounds(552, 220, 159, 72);
		getContentPane().add(button3_3);
		
		JButton button3_5 = new JButton("\u76EE\u6807\u4EE3\u7801");
		button3_5.setFont(new Font("����", Font.BOLD, 23));
		button3_5.setBounds(552, 422, 159, 72);
		getContentPane().add(button3_5);
		
		JButton button3_4 = new JButton("�����Ż�");
		button3_4.setFont(new Font("����", Font.BOLD, 23));
		button3_4.setBounds(552, 316, 159, 72);
		getContentPane().add(button3_4);
		
		
		
		button1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				textArea.setText("");
				
				JFileChooser file_open_filechooser = new JFileChooser();
				file_open_filechooser.setCurrentDirectory(new File("."));
				file_open_filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = file_open_filechooser.showOpenDialog(scrollPane1);	
				
				if (result == JFileChooser.APPROVE_OPTION) // ֤����ѡ��
				{
					file_name = file_open_filechooser.getSelectedFile().getPath();
					// ��ȡ�ļ���д��JTextArea����
					try
					{
						FileReader reader = new FileReader(file_name);
			            BufferedReader br = new BufferedReader(reader);
			            String line;

			            while ((line = br.readLine()) != null) 
			            {
							textArea.append(line);
							textArea.append("\n");
			            }
						reader.close();
					}
					catch(Exception event)
					{
						event.printStackTrace();
					}
					if (file_name.equals("") || textArea.getText().equals(""))
					{	
						JOptionPane.showMessageDialog(null,
								"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
					}
				} 
			}
		});

		button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("");
			}
		});
		

		button3_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (file_name.equals("") || textArea.getText().equals(""))
				{	
					JOptionPane.showMessageDialog(null,
							"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
				}
				Gui_1();
			}
		});

		button3_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (file_name.equals("") || textArea.getText().equals(""))
				{	
					JOptionPane.showMessageDialog(null,
							"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
				}
				Gui_2();
			}
		});

		button3_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (file_name.equals("") || textArea.getText().equals(""))
				{	
					JOptionPane.showMessageDialog(null,
							"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
				}
				Gui_3();
			}
		});

		button3_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (file_name.equals("") || textArea.getText().equals(""))
				{	
					JOptionPane.showMessageDialog(null,
							"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
				}
				Gui_4();
			}
		});
		
		button3_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (file_name.equals("") || textArea.getText().equals(""))
				{	
					JOptionPane.showMessageDialog(null,
							"û�пɷ����ĳ���,����ѡ��test.txt���ļ�", "Warning", JOptionPane.DEFAULT_OPTION);
				}
			    Gui_5();
			}
		});

		setVisible(true);    //���ô����Ƿ�ɼ�
	}
	
	
    public static void main(String[] agrs)
    {    	
        new Gui();    //����һ��ʵ��������
    }
}



