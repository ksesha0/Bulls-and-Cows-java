import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BullsAndCows extends JFrame{
	
	//各种控件
	JButton buttonNewGame = new JButton("新游戏");
	JButton buttonQuit = new JButton("退出");
			
	JLabel labelResult = new JLabel("这是结果");
	JLabel labelTime = new JLabel("时间");
	
	JLabel labelInput = new JLabel("Input:");
	JTextField textInputs[] = new JTextField[]{new JTextField(""),new JTextField(""),new JTextField(""),new JTextField("")};
	JButton buttonInput = new JButton("ok");
	
	JTextArea textResult = new JTextArea("");
		
	void setControlPos(int gridx,int gridy,int gridwidth,double weightx,double weighty, Component comp ,GridBagLayout gbLayout ){
		GridBagConstraints gbConstraint= new GridBagConstraints();
		gbConstraint.fill =  GridBagConstraints.BOTH;
		gbConstraint.gridx = gridx;
		gbConstraint.gridy = gridy;
		gbConstraint.weightx = weightx;
		gbConstraint.weighty = weighty;
		gbConstraint.gridwidth = gridwidth;
		gbLayout.setConstraints(comp, gbConstraint);
	}
	BullsAndCows(){
		setTitle("猜数字游戏 -by qhy 285571052");
		
		//界面布局
		//界面分成上，中，下 4块
		JPanel panelNorth = new JPanel(new GridLayout(1,2));
		JPanel panelInput = new JPanel();
		JPanel panelCenter = new JPanel(new GridLayout(1,1));
		JPanel panelSouth = new JPanel(new GridLayout(1,2));
		add(panelNorth);
		add(panelInput);		
		add(panelCenter);
		add(panelSouth);				
		
		GridBagLayout gbLayout= new GridBagLayout();							
		setControlPos(0,0,0,1,0,panelNorth,gbLayout);
		setControlPos(0,1,0,1,0,panelInput,gbLayout);
		setControlPos(0,2,0,1,1,panelCenter,gbLayout);
		setControlPos(0,3,0,1,0,panelSouth,gbLayout);										
		setLayout(gbLayout);
		
		//添加控件
		panelNorth.add(buttonNewGame);
		panelNorth.add(buttonQuit);				
		panelInput.add(labelInput);
		for( JTextField t:textInputs ){
			panelInput.add(t);
		}		
		panelInput.add(buttonInput);		
		panelCenter.add(textResult);			
		panelSouth.add(labelTime);	
		panelSouth.add(labelResult);
		
		//Input 块内布局
		GridBagLayout gbLayoutInput= new GridBagLayout();
		setControlPos(0,0,1,0,0,labelInput,gbLayoutInput);			
		for( JTextField t:textInputs ){
			setControlPos(GridBagConstraints.RELATIVE,0,1,1,0,t,gbLayoutInput);	
		}				
		setControlPos(GridBagConstraints.RELATIVE,0,0,0,0,buttonInput,gbLayoutInput);			
		panelInput.setLayout(gbLayoutInput);
				
		//响应关闭窗口
        this.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
        });
		
		//设置控件属性
		textResult.setEditable(false);
		setSize(500,500);
		setVisible(true);			        
	}
	public static void main(String[] args) {
		new BullsAndCows();
	}

}
