package com.test01;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ���ǰ�˷��͵Ĳ�ѯ����filter=%7B%22age%22%3A%2212%22%2C%22name%22%3A%22zx1%22%7D
 *                filter:{"age":"12","name":"zx1"}
 * ���Գ����Լ�ƴ��sql:select * from table where age='12' and name='zx1'              
 * @author user
 *
 */
public class JsonToSql {

	private String str;
	private long startTime;
	
	@Before
	public void before(){
		String strArray[]={"{\"name\":\"����\"}",
				           "{\"name\":\"����\",\"sex\":\"��\"}",
				           "{\"name\":\"����\",\"sex\":\"��\",\"age\":\"20\"}",
				           "",
				           null,
				           "{}"
		                  };
		str=strArray[2];
		System.out.println("jsonStr:"+str);
		System.out.println("��ʼ����...");
		startTime=System.nanoTime();
	}
	
	@After
	public void after(){
		System.out.println("�������...");
		System.err.println("��ʱ:"+(System.nanoTime()-startTime)+"ns");
	}
	
	@Test
	public void doString1(){//�ַ��������ȡ
		StringBuffer sb=new StringBuffer("select * from table");
		String tempStr=str;
		if(tempStr!=null&&tempStr.contains("\"")){
			sb.append(" where 1=1");
			tempStr=tempStr.replace("{", "").replace("}", "").replace("\"", "").replace(":", "=");
			String temp[]=tempStr.split(",");
			for (String string : temp) {
				string=string.replace("=", "='")+"'";
				sb.append(" and "+string);
			}			
		}	
		System.err.println("doString1�����:"+sb.toString());		
	}
	
	@Test
	public void doString2(){//�滻��,�ƺ�����ٶȸ���
		StringBuffer sb=new StringBuffer("select * from table");
		String tempStr=str;
		if(tempStr!=null&&tempStr.contains("\"")){
			tempStr=" where 1=1 and "+str.replace("{", "").replace("}", "").replace("\"", "").replace(":", "='")
					.replace(",", "' and ")+"'";
			sb.append(tempStr);			
		}	
		System.err.println("doString2�����:"+sb.toString());		
	}
	
}
