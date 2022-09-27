package poo.valutatoreEspressioni;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class FinestraGUI extends JFrame implements ActionListener
{	private JTextField input;
	private JTextField output=new JTextField("",	10);
	private Valutatore v=null;
	public FinestraGUI() 
	{	setTitle("Valuta Espresione");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel p=new JPanel();
		p.add(new JLabel("Inserire espressione",JLabel.RIGHT));
		p.add(input=new JTextField("",18));
		p.add(new JLabel("Risultato",JLabel.RIGHT));
		output.setEditable(false);
		p.add(output);
		add(p,BorderLayout.NORTH);
		JPanel q=new JPanel();
		q.add(new JLabel("Inserire espressione e premere ENTER",JLabel.RIGHT));
		add(q,BorderLayout.SOUTH);
		input.addActionListener(this);
		output.addActionListener(this);
		
		setLocation(200, 200);
		setSize(600,100);
			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	if(e.getSource()==input)
		{	v=new Valutatore(input.getText());
			int ris=0;
			try {
				ris=v.valuta();
			}catch(Exception exc)
			{	JOptionPane.showMessageDialog(null, "Stringa malformata reinserire..");
			}
			output.setText(ris+"");
		}
	}	
}

public class ValutatoreGUI {
	public static void main(String...args)
	{	FinestraGUI fg=new FinestraGUI();
		fg.setVisible(true);
	}
}
