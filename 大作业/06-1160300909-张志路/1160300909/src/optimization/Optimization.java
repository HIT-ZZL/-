package optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import semantic.FourAddr;
import semantic.Smantic;
import semantic.Symbol;

public class Optimization
{
	static List<List<FourAddr>> re = new ArrayList<List<FourAddr>>(); 
	static String block; 
	
	public Optimization(String filename)
	{ 	
		analyze(filename);
	}
	public List<List<FourAddr>> getRe()
	{
		return re;
	}
	
	public String getBlock()
	{
		return block;
	}
	
	/*public static void main(String[] args)
	{
		String b = new String();
		List<List<FourAddr>> r= new ArrayList<List<FourAddr>>();
		
		Optimization op = new Optimization("test.txt");
		//System.out.println(block);
	}*/
	
	public static void analyze(String filename)
	{
		List<FourAddr> four_addr = new ArrayList<FourAddr>();  // ����ַָ������
		Smantic se = new Smantic(filename, four_addr);
		List<List<FourAddr>> result = basicBlock(four_addr);
		re = sub_expression(result);
		block = printBlock(re);
		//System.out.println(block);
	}
	
	/**
	 * ɾ�������ӱ��ʽ
	 * @param result ɾ�������ӱ��ʽ��Ļ�����
	 * @return ɾ�������ӱ��ʽ��Ļ�����
	 */
	public static List<List<FourAddr>> sub_expression(List<List<FourAddr>> result)
	{
		for (int i=0; i<result.size(); i++)  // ÿ��������
		{
			for (int j=0; j<result.get(i).size(); j++)  // ��������ÿ����Ԫʽ
			{
				FourAddr f = result.get(i).get(j);
				boolean flag = false;
				
				Set<String> par = new HashSet<String>(); // �����ӱ��ʽ�󲿼���
				for (int k=j+1; k<result.get(i).size(); k++)  // ������
				{
					FourAddr f2 = result.get(i).get(k);
					if (f2.getToaddr().equals(f.getParam1()) 
							|| f2.getToaddr().equals(f.getParam2()))
					{
						// ����������
						break;
					}
					if (flag == true)
					{
						// �������Ƿ��жԹ����ӱ��ʽ�󲿵����á����У��ı������
						if (par.contains(f2.getParam1()))
						{
							result.get(i).get(k).setParam1(f.getToaddr());
						}
						if (par.contains(f2.getParam2()))
						{
							result.get(i).get(k).setParam2(f.getToaddr());
						}
						if (par.contains(f2.getToaddr()))
						{
							result.get(i).get(k).setToaddr(f.getToaddr());
						}
					}

					if (f2.getParam1().equals(f.getParam1()) 
							&& f2.getParam2().equals(f.getParam2())
							&& f2.getOp().equals(f.getOp()))
					{
						flag = true;
						par.add(f2.getToaddr());
						result.get(i).get(k).setOp("-");
						result.get(i).get(k).setParam1("-");
						result.get(i).get(k).setParam2("-");
						result.get(i).get(k).setToaddr("-");
					}
				}
			}
		}
		return result;
	}
	
	
	
	public static String printBlock(List<List<FourAddr>> f)
	{
		StringBuffer s = new StringBuffer();
		int n = 1;
		for (int i=0; i<f.size(); i++)
		{
			s.append("B"+(i+1)+":\n");	
			for (int j=0; j<f.get(i).size(); j++)
			{
				s.append(n+": \t");	
				s.append(f.get(i).get(j).toString());	
				s.append("\n");
				n++;
			}
			s.append("\n");
		}
		//System.out.println(s);
		return s.toString();	
	}
	
	/**
	 * ���ֽ��
	 * @param list ��Ԫʽ����
	 * @return ���ֽ��
	 */
	public static List<List<FourAddr>> basicBlock(List<FourAddr> list)
	{
		List<List<FourAddr>> result = new ArrayList<List<FourAddr>>(); 
		List<FourAddr> block = new ArrayList<FourAddr>(); 
		List<Integer> index = indexBlock(list);
		//System.out.println(index);
		int num = 1;
		int n = index.get(num);
		
		for(int i = 0;i<list.size();i++)
		{	
			block.add(list.get(i));
			if (n-1 == i)
			{
				result.add(block);
				block = new ArrayList<FourAddr>(); 
				num++;
				if (num < index.size())
				{
					n = index.get(num);
				}
				else
				{
					n = list.size();
				}
			}
		}
		return result;
		//return re;
	}
	
	/**
	 * ������������ʼ���б�
	 * @param list ��Ԫʽ����
	 * @return ���������ʼ���б�
	 */
	public static List<Integer> indexBlock(List<FourAddr> list)
	{
		Set<Integer> index = new HashSet<Integer>(); 
		index.add(0);
		
		for(int i = 0;i<list.size();i++)
		{
			String s = list.get(i).getOp();
			if (s.contains("j"))
			{
				int n = Integer.valueOf(list.get(i).getToaddr());
				index.add(n-1);
				index.add(i+1);
			}
		}

		List<Integer> result = new ArrayList<Integer>(index);
		Collections.sort(result);
		return result;
	}

}
