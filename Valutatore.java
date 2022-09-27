package poo.valutatoreEspressioni;
import java.util.StringTokenizer;



public class Valutatore {
	private String condizioneNecessaria="(\\d+|[\\+\\-\\^\\%\\*/\\(\\)])+";
	private String op1="\\^";
	private String op2="\\*|\\/|\\%";
	private String op3="\\+|\\-";
	
	private String input;
	
	
	public Valutatore(String input)
	{	if(!input.matches(condizioneNecessaria))
			throw new IllegalArgumentException("Stringa malformata");
		this.input=input;
	}
	public int valuta()
	{	StringTokenizer st=new StringTokenizer(input,"+-*/%^()",true);
		return valutaEspressione(st);
	}//valuta
	
	
	private int valutaEspressione(StringTokenizer st)
	{	Stack<Integer>operandi=new StackConcatenato<Integer>();
		Stack<Character>operatori=new StackConcatenato<Character>();
		String token=st.nextToken();
		while(st.hasMoreTokens() && !token.equals(")"))
		{	if(token.matches("\\d+"))
				operandi.push(Integer.parseInt(token));
			else if(token.equals("("))
			{	int ris=valutaEspressione(st);
				operandi.push(ris);
				if(!st.hasMoreTokens())   //se ')' è l'ultima interrompo perche se no interrompo prima e non prendo l'operatore successivo
					continue;
			}
			else if(token.matches("\\D"))
			{	char opc=token.charAt(0);
				if(operatori.isEmpty() || prioritario(opc,operatori.peek()))
					operatori.push(token.charAt(0));
				else
				{	
					int o2=operandi.pop();
					int o1=operandi.pop();
					char op=operatori.pop();
					operandi.push(opera(o1,op,o2));
					operatori.push(opc);
				}
			}
			token=st.nextToken();
		}
		if(!token.equals(")") && !token.equals("("))  //esco dal while prima quindi devo mettere l'ultimo numero fuori il ciclo
			operandi.push(Integer.parseInt(token)); 
		while(operandi.size()>1)
		{	int o2=operandi.pop();
			int o1=operandi.pop();
			char op=operatori.pop();
			operandi.push(opera(o1,op,o2));
		}
		return operandi.pop();
		
	}
	

	private boolean prioritario(char opc, char peek) // scrivere quando è true, quindi l'else ret false visto che +- da sempre falso qualiasi sia peek
	{	String opcS=opc+"",peekS=peek+"";
		if(opcS.matches(op1) && !peekS.matches(op1))// || opcS.matches(op1) && peekS.matches(op3)) 
			return true;
		else if(opcS.matches(op2) && peekS.matches(op3))
			return true;
		else
			return false;
	}
	
	private int opera(int o1, char op, int o2)
	{	switch (op) 
		{	case '+': return o1+o2;
			case '-': return o1-o2;
			case '*': return o1*o2;
			case '/': return o1/o2;
			case '%': return o1%o2;
			case '^': return (int)Math.pow(o1,o2);
			default:
				throw new IllegalArgumentException("Unexpected value: " + op);
		}
	}

}
