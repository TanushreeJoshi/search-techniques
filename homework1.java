package homework1;

import java.io.*;
import java.util.*;

class Node
{
	char[][] state;
	int colExplored;
	int liz;
	int noCE;
	Node(char[][] c,int c1,int n,int nC)
	{
		state=c;
		colExplored=c1;
		liz=n;	
		noCE=nC;
	}
}
class ConflictNode{
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	Map<Integer,List> conf= new HashMap();
	 Integer m=0;
	 @SuppressWarnings("rawtypes")
	ConflictNode(Integer c,Map<Integer, List> mp)
	 {
		 m=c;
		 conf=mp;
	 }
}
public class homework1 {
	static char[][] c;
	int l=0;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Queue<Node> q = new LinkedList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Stack<Node> st=new Stack();
	static char[][] createCopy(char[][] c,int n)
	{	char[][] temp= new char[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				temp[i][j]=c[i][j];
		return temp;
	}
	static boolean checkGoal(int p,char[][] c,int n)
	{
		int count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(c[i][j]=='1')count++;
		if(count==p)return true;
		return false;
	}
	static char[][] DFS(int n,int p2,Node root)
	{
		//System.out.println("DFS called");
		long stt= System.currentTimeMillis();
		  long end = stt + 290*1000;
		int N = 1; char[][] start=root.state;
		int liz=p2;
		while(N<=n)	
		{
		while(!st.isEmpty())
		{
			if(System.currentTimeMillis()>=end)return null;
		Node ab =st.pop();
		char[][] c=ab.state;int nCE = ab.noCE;
		if(	checkGoal(liz,c,n)) return c;
		int col=ab.colExplored;  int l=ab.liz;
	    int cole=col+1; int nC = nCE+1;
			if(col==n-1)cole=0;
		char[][] temp= new char[n][n];
		int change=0; int colCounter=0;
		if(nCE<n)
		{
			for(int a=0;a<n;a++)
		
		{  
			if(c[a][col]=='0')
				{ 
				temp= createCopy(c,n);
				temp[a][col]='1'; change=1;
				int s=a,t=col+1;
				while(t<n&&temp[s][t]!='2')  //same row next col
				{
		        if(temp[s][t]!='1')temp[s][t]='x';
		        t++;
				} 
				t=col-1; s=a;
				while(t>=0&&temp[s][t]!='2') // same row prev col
				{
		        if(temp[s][t]!='1')temp[s][t]='x';
		        t--;
				}
			    t=col; s=a+1;
				while(s<n&&temp[s][t]!='2')    // next row same col
				{  
				if(temp[s][t]!='1')temp[s][t]='x';
				s++;
				}
				if(s<n)colCounter=1;
				s=a-1; t=col;
				while(s>=0&&temp[s][t]!='2')    // prev row same col
				{  
				if(temp[s][t]!='1')temp[s][t]='x';
				s--;
				}
				if(s>=0)colCounter=1;
				s=a+1; t=col+1;            
				while(s<n && t<n && temp[s][t]!='2')  // diag right bottom
				{
					if(temp[s][t]!='1')temp[s][t]='x';
					s++; t++;
				}
				s=a-1; t=col-1;
				while(s>=0&&t>=0&&temp[s][t]!='2')   // diag left upper
				{
					if(temp[s][t]!='1')temp[s][t]='x';
						s--; t--;
				}
				s=a; t=col;
				while(s>=0 && t<n)                       //diag left upper
				{ 
					if(temp[s][t]=='2') break;
					else if(temp[s][t]!='1')temp[s][t]='x';
					--s; ++t;
				}
				s=a; t=col;
				while(t>=0 && s<n)                // diag right bottom
				{ 
					if(temp[s][t]=='2') break;
					else if(temp[s][t]!='1')temp[s][t]='x';
					--t; ++s;
				}
				if(colCounter==1) {cole=col;nC=nCE;}
				Node t1= new Node(temp,cole,l+1,nC);
				st.push(t1); 
					}
				}
			
	   if(change==0) {
		   temp= createCopy(c,n);
					Node t1= new Node(temp,cole,l,nC);
					st.push(t1);	   
			      
	          }
		}
	   }
		N++;
		Node n1 = new Node(start,N-1,0,0); st.push(n1);
	}
	return null;
	}
	static char[][] BFS(int n,int p,Node root)
	{// System.out.println("BFS called");
		//long stt= System.currentTimeMillis();
		  // long end = stt + 290*1000;
		int liz=p;
	int N = 1; char[][] start=root.state;
	while(N<=n)
	{
		while(!q.isEmpty())
		
	{
			//if(System.currentTimeMillis()>=end)return null;
	Node ab =q.remove();
	 c=ab.state;
	int l =ab.liz;
	if(	checkGoal(liz,c,n)) {
		return c;
	}
	int col=ab.colExplored;  int nCE=ab.noCE;
    int cole=col+1; int nC = nCE+1;
	if(col==n-1)cole=0;
	char[][] temp= new char[n][n];
	int change=0;int colCounter=0;
	if(nCE<n)
	{
		for(int a=0;a<n;a++)
	{  
		if(c[a][col]!='x'&c[a][col]!='2'&c[a][col]!='1')
			{ 
			temp= createCopy(c,n);
			temp[a][col]='1'; change=1;
			int s=a,t=col+1;
			while(t<n&&temp[s][t]!='2')  //same row next col
			{
	        if(temp[s][t]!='1')temp[s][t]='x';
	        t++;
			} 
			t=col-1; s=a;
			while(t>=0&&temp[s][t]!='2') // same row prev col
			{
	        if(temp[s][t]!='1')temp[s][t]='x';
	        t--;
			}
		    t=col; s=a+1;
			while(s<n&&temp[s][t]!='2')    // next row same col
			{  
			if(temp[s][t]!='1')temp[s][t]='x';
			s++;
			}
			if(s<n)colCounter=1;
			s=a-1; t=col;
			while(s>=0&&temp[s][t]!='2')    // prev row same col
			{  
			if(temp[s][t]!='1')temp[s][t]='x';
			s--;
			}
			if(s>=0)colCounter=1;
			s=a+1; t=col+1;            
			while(s<n && t<n && temp[s][t]!='2')  // diag right bottom
			{
				if(temp[s][t]!='1')temp[s][t]='x';
				s++; t++;
			}
			s=a-1; t=col-1;
			while(s>=0&&t>=0&&temp[s][t]!='2')   // diag left upper
			{
				if(temp[s][t]!='1')temp[s][t]='x';
					s--; t--;
			}
	
			s=a; t=col;
			while(s>=0 && t<n)                       //diag left upper
			{ 
				if(temp[s][t]=='2') break;
				else if(temp[s][t]!='1')temp[s][t]='x';
				--s; ++t;
			}
			s=a; t=col;
			while(t>=0 && s<n)                // diag right bottom
			{ 
				if(temp[s][t]=='2') break;
				else if(temp[s][t]!='1')temp[s][t]='x';
				--t; ++s;
			}
			if(colCounter==1) {cole=col;nC=nCE;}
				Node t1= new Node(temp,cole,l+1,nC);
			q.add(t1);
				
			}
		}
   if(change==0) {
	   temp= createCopy(c,n);
		Node t1= new Node(temp,cole,l,nC);
		q.add(t1);   
     }
	
	}

}
		N++;
		Node n1 = new Node(start,N-1,0,0); q.add(n1);
}
	return null;
	
}
	static ConflictNode numConflicts(char[][] c,int n)
	{  
		 @SuppressWarnings({ "rawtypes", "unchecked" })
		Map<Integer,List> conf= new HashMap(); Integer m=0,recent=0;
		int counter=0,conflicts=0; int curcol=0;int currow=0;
		int col=0;int row=0; int a=0;
		while(curcol<n) 
		{    col=curcol;
			counter=0; a=currow;
		for( ;a<n;a++)                 //same col
		{  if(c[a][col]=='2') {
			currow=a+1; curcol=col; break;
		     }
			if(c[a][col]=='1') {
				
	if(!conf.containsValue(Arrays.asList((Integer)a,(Integer)col)))
		{conf.put(m, Arrays.asList((Integer)a,(Integer)col));
		counter++;
		recent=m;
			m++;}
			}
			
		}
		if(a>=n-1) {
			curcol++; currow=0;
		}
		if(counter==1) {conf.remove(recent);m=recent;}
		if(counter>1)conflicts+=counter-1;
		}
		curcol=0; currow=0;
		while(currow<n) 
		{    a=curcol;
			counter=0; row=currow;
		for( ;a<n;a++)                 //same row
		{  if(c[row][a]=='2') {
			curcol=a+1; currow=row; break;
		     }
			if(c[row][a]=='1') {
				
	if(!conf.containsValue(Arrays.asList((Integer)row,(Integer)a)))
		{conf.put(m, Arrays.asList((Integer)row,(Integer)a));
		counter++;
		recent=m;
			m++;}
			}
			
		}
		if(a>=n-1) {
			currow++; curcol=0;
		}
		if(counter==1) {conf.remove(recent);m=recent;}
		if(counter>1)conflicts+=counter-1;
		}
		
		col=0;row=n-2;counter=0; int r=row;   //bottom left half
		curcol=col;currow=row;
		while(currow>=0)
		{  row=currow; col=curcol;
			while(row<n&&col<n)
			{   if(c[row][col]=='2') {
				curcol=col+1; currow=row+1; break;
		         }
				if(c[row][col]=='1') {
				if(!conf.containsValue(Arrays.asList((Integer)row,(Integer)col))) {conf.put(m,Arrays.asList((Integer)row,(Integer)col));
				counter++;recent=m; m++;}
				}
				row++;col++;
			}
			if(row>=n-1) {
				curcol=0;r--;currow=r;
			}
			if(counter==1){conf.remove(recent);m=recent;}
			if(counter>1)conflicts+=counter-1;
			 counter=0;
		}
		
		col=1;row=0;counter=0; int cl=col;   //upper right half
		curcol=1; currow=0;
		while(curcol<=n-1)
		{   col=curcol; row=currow;
			while(row<n&&col<n)
			{
				if(c[row][col]=='2') {
				curcol=col+1; currow=row+1; break;
	         }
				if(c[row][col]=='1') {
				if(!conf.containsValue(Arrays.asList((Integer)row,(Integer)col))) {conf.put(m, Arrays.asList((Integer)row,(Integer)col));
				counter++;recent=m;m++;}
				}
				row++;col++;
			}
			if(col>=n-1) {
				cl++;curcol=cl;currow=0;
			}
			if(counter==1){conf.remove(recent);m=recent;}
			if(counter>1)conflicts+=counter-1;
			counter=0;
		}
	
		col=1;row=0;counter=0; cl=col;          //upper left
		curcol=1; currow=0;
		while(curcol<n)
		{   col=curcol; row=currow;
			while(row<n&col>=0)
			{
				if(c[row][col]=='2') {
					curcol=col-1; currow=row+1; break;
		         }
				if(c[row][col]=='1')
					{
					if(!conf.containsValue(Arrays.asList((Integer)row,(Integer)col))) {conf.put(m,Arrays.asList((Integer)row,(Integer)col));
					counter++;recent=m;m++;}
					}
				row++;col--;
			}
			if(col<=0) {
				cl++;curcol=cl;currow=0;
			}
			if(counter==1){conf.remove(recent);m=recent;}
			if(counter>1)conflicts+=counter-1;
			counter=0;
		}
		
		col=n-1;row=1;counter=0;r=row;  //bottom right
		curcol=n-1;currow=1;
		while(currow<n)
		{   col=curcol; row=currow;
			while(row<n&col>0)
			{
				if(c[row][col]=='2') {
				curcol=col-1; currow=row+1; break;
	            }
				if(c[row][col]=='1')
					{
					if(!conf.containsValue(Arrays.asList((Integer)row,(Integer)col))) {conf.put(m, Arrays.asList((Integer)row,(Integer)col));
					counter++;recent=m;m++;}
					}
				row++;col--;
			}
			if(row>=n-1) {
				curcol=n-1;r++;currow=r;
				
			}
				if(counter==1){conf.remove(recent);m=recent;}
			if(counter>1)conflicts+=counter-1;
			counter=0;
		}
		//System.out.println(m+" "+conflicts);
	return new ConflictNode(m,conf);
	}
	
	static char[][]  SA(int n,int p,char[][] c)
	{ //System.out.println("SA called");
		long start = System.currentTimeMillis();
	   long end = start + 280*1000;
		 char[][] init=c; int count=0;int x=0,y=0;//create initial position
		double T=  Double.MAX_VALUE;  int available=0;
		for(int r=0;r<n;r++)
			for(int q=0;q<n;q++)
				if(c[r][q]=='0')available++;
		if(available<p)return null;
	 while(count!=p)
		 { 
			 x=(int)(Math.random()*n); //System.out.println(x);
			 y=(int)(Math.random()*n);//System.out.println(y);
			 if(init[x][y]=='0') 
			 {
				 init[x][y]='1'; count++;
			 }
		 }
		 char[][] next=createCopy(init,n);
		 ConflictNode c1,c2; int x1=0,y1=0,x2=0,y2=0,d=0; double pb=0,temp=0; int t=3; 
		 double f=0,e=0;
		 int size=0,pos=0;
		 int con1=0,con2=0;
	
			 while(T>0)
		 {
				 if (System.currentTimeMillis() >= end)break;
			 //System.out.println("T: "+T+" t:"+t	); 
			 next=createCopy(init,n);
			 c1=numConflicts(init,n); Map m1 = c1.conf; con1=(int)c1.m;
			 if(con1==0)return init;
			 size=m1.size();  pos= (int) (Math.random()*size);
			 List l =(List) m1.get(pos); m1.remove(pos);
			 x1=(int)l.get(0); y1=(int)l.get(1);
			 do {
			 x2=(int)(Math.random()*n);            		 //create random neighbour
			 y2=(int)(Math.random()*n);
			 } while((x2==x1&&y2==y1)||init[x2][y2]!='0');
	
			next[x2][y2]='1'; next[x1][y1]='0';
			c2=numConflicts(next,n); Map m2 = c2.conf; con2=(int)c2.m;
			d=con2-con1;
			if(con2==0)return next;
			if(con2<=con1)init=next;
			else {
				temp=d/T; 
				pb= Math.exp(-1*temp);
				if(Math.random()<pb)init=next;
				else next=init;
			
			}
			f=(Math.random());
			T = (double)1/Math.log(f+t);
			t++;
		 }
		 
	 
		 return null;
	}
	
	public static void main(String[]args)
	{
	FileInputStream in =null;
	FileOutputStream out=null;
	String algo; int n=0,p=0;

	try {
		in= new FileInputStream("input.txt");
		out = new FileOutputStream("output.txt");
		BufferedReader colr = new BufferedReader(new InputStreamReader(in));
		PrintStream pw = new PrintStream(out);
	String s;
	String[] line = new String[3];
	int i=0;
	    while(i<3){
	 line[i] = colr.readLine();
	      i++;
	    }
		algo=line[0].trim();
		n=Integer.parseInt(line[1]);
		p=Integer.parseInt(line[2]);
		String[] board= new String[n]; 
		i=0; int counter=0;
		 while((s = colr.readLine()) != null && counter<n){
				 board[i] = s;
			      i++;counter++;
			 }
		char[][] state=new char[n][n];
		int k=0;
		for(int j=0;j<n;j++)
		{
		 state[k++]=board[j].toCharArray();
		}
		Node root =new Node(state,0,0,0);
		char[][] result=null;
		if(algo.equals("BFS")) {
			q.add(root);
			result = BFS(n,p,root);
		}

		if(algo.equals("DFS")) {
			st.push(root);
			 result = DFS(n,p,root);
		}
		if(algo.equals("SA")) {   
			st.push(root);
			 result = SA(n,p,state);
		}
	    
	    	if(result==null)
	    		{
	    		System.out.println("FAIL");
	    		 pw.print("FAIL");
	    		}
	    	else {
	    		System.out.println("OK");
	    		 pw.print("OK");
	    		 pw.println();
	    for(int z=0;z<n;z++)
			{
	    	for(int j=0;j<n;j++)
				{
	    		System.out.print(result[z][j]+" ");
				if(result[z][j]=='x')pw.print('0');
				else pw.print(result[z][j]);
				}
	    	pw.println();
			System.out.println();
			}

	    	}	
	}
	catch (Exception e) {
		
		e.printStackTrace();
	}

	}

	
}

