package grammar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import lexical.Lexical;
import lexical.Token;


public class SyntaxParser 
{
	
	private Lexical lex;  // �ʷ�������
	private ArrayList<Token> tokenList = new ArrayList<Token>();  // �Ӵʷ���������õ�����token
	private int length;  // tokenlist�ĳ���
	private int index;  // �﷨�������е���λ��
	
	private AnalyzeTable table;  //������﷨������
	private Stack<Integer> stateStack;  //���ڴ洢��Ӧ��DFA״̬��
	private static StringBuffer result = new StringBuffer();  // �����Լ���

	private static List<String> result2 = new ArrayList();  // �����Լ���
	private static List<String> errors = new ArrayList();  // �����Լ���
	
	public static ArrayList<Tree> tree = new ArrayList<Tree>();  // �﷨��
	private Stack<TreeNode> treeNodeID = new Stack<TreeNode>();;  // ���ڴ洢��Ӧ�����ڵ�
	private int count;  // ��¼�ڵ��
	//private Error error = null;
	/*
	public static void main(String[] args)
	{
		SyntaxParser parser = new SyntaxParser("test1.txt", tree);
	}
	*/
	
	/**
	 * ��������ļ��������ַ���
	 * @param filename �ļ���
	 * @return �ļ�����
	 */
	public static String readfile(String filename)
	{
		StringBuffer result = new StringBuffer();
		File file = new File(filename);
		try
		{			
			InputStream in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte=in.read()) != -1) 
			{
				result.append(""+(char)tempbyte);
			}
			in.close();
		}
		catch(Exception event)
		{
			event.printStackTrace();
		}
		return result.toString();
	}
	
	public SyntaxParser(String filename, List<String> result2, List<String> errors)
	{
		this.lex = new lexical.Lexical(readfile(filename), this.tokenList);
		this.lex.analyze();  // �ʷ�����
		int last = tokenList.get(tokenList.size()-1).line + 1;
		this.tokenList.add(new Token(last,"#",-1));		
		this.length = this.tokenList.size();
		
		this.index = 0;

		/*this.table = new AnalyzeTable();  // ���ɷ�����
		try
		{  
		    FileOutputStream fs = new FileOutputStream("AnalyzeTable");  
		    ObjectOutputStream os =  new ObjectOutputStream(fs);  
		    os.writeObject(this.table);  
		    os.close();  
		}
		catch(Exception ex)
		{  
		    ex.printStackTrace();  
		} */
		
		try
		{
			FileInputStream fs = new FileInputStream("AnalyzeTable");
			ObjectInputStream in =  new ObjectInputStream(fs);
			this.table = (AnalyzeTable) in.readObject();			
		    in.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
		
		this.stateStack = new Stack<Integer>();  // ״̬ջ
		this.stateStack.push(0);  // ��ʼΪ0״̬
		
		//this.table.dfa.writefile();  // д���ļ�"DFA_state_set.txt"		
		//this.table.print();  // д���ļ�"LR_analysis_table.txt"

		for(int i = 0;i < tokenList.size();i++)
		{
			result.append(tokenList.get(i).toString() + "\n");
		}
		SyntaxParser.result2 = result2;
		SyntaxParser.errors = errors;
		
		analyze();
		//printTree();
		//writefile(result);
	}
	
	
	public SyntaxParser(String filename, ArrayList<Tree> tree)
	{		
		this.lex = new lexical.Lexical(readfile(filename), this.tokenList);
		this.lex.analyze();  // �ʷ�����
		int last = tokenList.get(tokenList.size()-1).line + 1;
		this.tokenList.add(new Token(last,"#",-1));		
		this.length = this.tokenList.size();
		
		this.index = 0;
		
		/*this.table = new AnalyzeTable();  // ���ɷ�����
		try
		{  
		    FileOutputStream fs = new FileOutputStream("AnalyzeTable");  
		    ObjectOutputStream os =  new ObjectOutputStream(fs);  
		    os.writeObject(this.table);  
		    os.close();  
		}
		catch(Exception ex)
		{  
		    ex.printStackTrace();  
		} */
		
		try
		{
			FileInputStream fs = new FileInputStream("AnalyzeTable");
			ObjectInputStream in =  new ObjectInputStream(fs);
			this.table = (AnalyzeTable) in.readObject();			
		    in.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
		
		this.stateStack = new Stack<Integer>();  // ״̬ջ
		this.stateStack.push(0);  // ��ʼΪ0״̬
		
		//this.table.dfa.writefile();  // д���ļ�"DFA_state_set.txt"		
		//this.table.print();  // д���ļ�"LR_analysis_table.txt"
		
		this.tree = tree;

		for(int i = 0;i < tokenList.size();i++)
		{
			result.append(tokenList.get(i).toString() + "\n");
		}
		
		analyze();
		//printTree();
		//writefile(result);
	}
	
	public Token readToken()
	{
		if(index < length)
		{
			return tokenList.get(index++);
		} 
		else 
		{
			return null;
		}
	}
	
	/**
	 * �����ֱ����Ӧ���ķ�����
	 * @param valueType
	 * @return
	 */
	private String getValue(Token valueType)
	{
		try
		{
			int code = valueType.code;
			if(code == 1)
				return "id";
			else if(code == 2)
				return "num";
			else if(code < 400 && code >=101)
				return valueType.value;
			else if(valueType.value.equals("#"))
				return "#";
			else
				return " ";
		}
		catch(Exception NullPointerException)
		{
			return "";	
		}
	}
	
	/**
	 * ���岿�� �﷨����
	 */
	public void analyze()
	{
		result.delete(0,result.length()-1);  // �����Լ���
		result2.clear();  // �����Լ���
		errors.clear();  // �����Լ���
		tree.clear();  // �﷨��
		treeNodeID.clear();  // ���ڴ洢��Ӧ�����ڵ�
		
		int m = 0;
		int last=1;
		while(true)
		{			
			result.append("��ǰ������: ");
			//System.out.print("��ǰ������: ");
			printInput();
			result.append("\n\n");
			//System.out.println();
			//System.out.println();
			
			Token token = readToken();
			String value = getValue(token);
			
			if(value.equals(""))
			{
				error();
				m++;
				if(m>50)
				{
					System.out.println("�������ķ�");
					break;
				}
				continue;
			}
			else if(value.equals(" "))
			{
				continue;
			}
	
			int state = stateStack.lastElement();
			String action = table.ACTION(state, value);		
			//System.out.println(action);
			if(action.startsWith("s"))
			{
				int newState = Integer.parseInt(action.substring(1));
				stateStack.push(newState);
				//System.out.print("����"+"\t");
				result.append("����"+"\t");
				//System.out.print("״̬��:"+stateStack.toString()+"\t");
				
				treeNodeID.push(new TreeNode(count++,value,token.value,last));  
			} 
			else if(action.startsWith("r"))
			{
				Production derivation = GrammarProc.F.get(Integer.parseInt(action.substring(1)));

				//System.out.println(derivation);
				result.append(derivation + "\n");
				result2.add(derivation.toString());
				int r = derivation.list.size();
				index--;
				
				LinkedList<TreeNode> son = new LinkedList<TreeNode>(); 
				if(!derivation.list.get(0).equals("��"))
				{
					for(int i = 0;i < r;i++)
					{				
						stateStack.pop();
						
						son.addFirst(treeNodeID.pop()); 
					}
				}

				int s = table.GOTO(stateStack.lastElement(), derivation.left);
				stateStack.push(s);
				result.append("��Լ"+"\t");
				
				treeNodeID.push(new TreeNode(count++,derivation.left,"--",last)); 
				ArrayList<TreeNode> sonList = new ArrayList<TreeNode>(son);
				tree.add(new Tree(treeNodeID.peek(),sonList));              
			} 
			else if(action.equals(AnalyzeTable.acc))
			{
				//System.out.print("�﷨�������"+"\t"+"\n");
				result.append("�﷨�������"+"\t");
				return;
			} 
			else 
			{
				error();
				m++;
				if(m>50)
				{
					System.out.println("�������ķ�");
					break;
				}
				while(action.startsWith("r"))
				{
					index = index - 1;
					Token token1 = readToken();
					tokenList.remove(token1);
					
					if(tokenList.size()==0)
						break;
					index = index - 1;
					
					String value1 = getValue(token1);
					stateStack.pop();

					if(value.equals(""))
					{
						error();
						m++;
						if(m>50)
						{
							System.out.println("�������ķ�");
							break;
						}
						continue;
					}
					if(value.equals(" "))
						continue;
					
					int state1 = stateStack.lastElement();
					action = table.ACTION(state1, value1);
					//System.out.println(action);					
				}
			}
			if(m>50)
			{
				System.out.println("�������ķ�");
				break;
			}
			last = token.line;
		}
	}
	
	

	/**
	 * ����
	 */
	public void error()
	{
		String s = "Error at Line[" + tokenList.get(index-1).line + "]:  \""+
				tokenList.get(index-1).value + "\" ���ʴ������˴���";
		result.append(s);
		errors.add(s);
		//System.out.println(s);
	}
	
	/**
	 * ������
	 */
	private static void writefile(StringBuffer str)
	{
        String path = "LR_Analysis_Result.txt";
        try 
        {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str.toString()); 
            bw.close(); 
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * ��ӡ��������ĳ���
	 */
	private void printInput()
	{
		String output = "";
		for(int i = index;i < tokenList.size();i++)
		{
			output += tokenList.get(i).value;
			output += " ";
		}
		//System.out.print(output);
		result.append(output);
	}
	
	
	/**
	 * ��ӡ�﷨��
	 */
	private static void printTree()
	{
		String output = "\n";
		for(int i=0; i<tree.size(); i++)
		{
			output += tree.get(i).toString();
			output += "\n";
		}
		//System.out.print(output);
		result.append(output);
	}
}
