import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class BullsAndCows extends JFrame{
	
	//���ֿؼ�
	JButton buttonNewGame = new JButton("����Ϸ");
	JButton buttonQuit = new JButton("�˳�");
			
	JLabel labelResult = new JLabel("���ǽ��");
	JLabel labelTime = new JLabel("ʱ��");
	
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
		setTitle("��������Ϸ -by qhy 285571052");
		
		//���沼��
		//����ֳ��ϣ��У��� 4��
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
		
		//��ӿؼ�
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
		
		//Input ���ڲ���
		GridBagLayout gbLayoutInput= new GridBagLayout();
		setControlPos(0,0,1,0,0,labelInput,gbLayoutInput);			
		for( JTextField t:textInputs ){
			setControlPos(GridBagConstraints.RELATIVE,0,1,1,0,t,gbLayoutInput);	
		}				
		setControlPos(GridBagConstraints.RELATIVE,0,0,0,0,buttonInput,gbLayoutInput);			
		panelInput.setLayout(gbLayoutInput);
				
		//��Ӧ�رմ���
        this.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
        });		
        
        buttonNewGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
System.out.println("buttonNewGame");
			}});
        
        buttonQuit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
System.out.println("Quit");
				System.exit(0);
			}});
                
        buttonInput.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
System.out.println("buttonInput");
			}});
        
        		
		//����4���༭��ֻ���������
		for( JTextField t:textInputs ){
			t.addKeyListener(new KeyListener(){
				@Override
				public void keyPressed(KeyEvent arg0) {
					//�� BACK-SPACE ���������ǰ�༭��Ϊ�գ���ô���˻ص���һ���༭�򣬷���shift+tab ��Ϣ��ȥ
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE && t != textInputs[0] && t.getText().equals("")){								
						KeyEvent ke = new KeyEvent(t,KeyEvent.KEY_PRESSED,0,1,KeyEvent.VK_TAB,KeyEvent.CHAR_UNDEFINED);
						t.dispatchEvent(ke);
					}else if( arg0.getKeyChar() == KeyEvent.VK_ENTER ){
System.out.println("EnterPressed");
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {}
				@Override
				public void keyTyped(KeyEvent arg0) {
					//��δ��벻�ܷ��� keyPressed �ϣ������޷��ڱ༭������ʾ
					if(Character.isDigit(arg0.getKeyChar()) == false){	
						arg0.consume();
					}else {		
						t.setText("");
						//û����һ�Σ��л�����һ��
						if(t != textInputs[textInputs.length - 1]){
							KeyEvent ke = new KeyEvent(t,KeyEvent.KEY_PRESSED,0,0,KeyEvent.VK_TAB,KeyEvent.CHAR_UNDEFINED);
							t.dispatchEvent(ke);											
						}
					}
				}				
			});
		}
		
		//���ÿؼ�����
		textResult.setEditable(false);
		setSize(500,500);
		setVisible(true);	
		//�༭���ȡ����
		textInputs[0].requestFocus();
	}
	public static void main(String[] args) {
		new BullsAndCows();
	}

}
