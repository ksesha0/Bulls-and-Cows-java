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
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	//各种控件.
	JButton buttonNewGame = new JButton("新游戏");
	JButton buttonQuit = new JButton("退出");
			
	JLabel labelResult = new JLabel("游戏开始!");
	JLabel labelTime = new JLabel("时间:");
	
	JLabel labelInput = new JLabel("Input:");
	JTextField textInputs[] = new JTextField[]{new JTextField(""),new JTextField(""),new JTextField(""),new JTextField("")};
	JButton buttonInput = new JButton("ok");
	
	JTextArea textResult = new JTextArea("");
	
	int nums[] = new int[4];
	int numInput[] = new int[4];
	int cntTimes;
	final int cntMaxTimes = 8;
	Random random = new Random();
	//计时
	int cntTime,delay = 1000;
	Timer timer = new Timer();
	QhyTimerTask task = new  QhyTimerTask();
	boolean isCountTime,isGameOver;
	
	//初始化游戏
	void iniGame(){
		//生成随机4个数
		for(int i = 0 ; i < nums.length;++i){
			nums[i] = random.nextInt(10);
			textInputs[i].setText("");
System.out.print(nums[i]);
		}
		textInputs[0].requestFocus();
		//输出各种状态
		textResult.setText("Times\tGuess\tResult\n");
		cntTimes = 0;	
		cntTime = -1;
		isCountTime = true;				
		labelResult.setText("游戏已经开始!");		
	}
	
	void gameOver(boolean isWin){
		//游戏结束
		isCountTime = false;
		if(isWin){
			labelResult.setText("GameOver：你赢了！");
			textResult.setText(textResult.getText() + "GameOver：你赢了！\t 耗时:" + cntTime + "s\n" );
		}else {
			textResult.setText(textResult.getText()  + "GameOver：超过" + cntMaxTimes + "次\t 耗时:" + cntTime + "s\n" );
			labelResult.setText("GameOver：超过" + cntMaxTimes + "次\n");
		}
	}
	
	//判断输入和输赢
	void judgeGame(){
		if(isCountTime){
			//获取填空
			for(int i = 0 ; i < 4;++i){		
				try{
					numInput[i] = Integer.parseInt(textInputs[i].getText());
				}catch (NumberFormatException e){
					labelResult.setText("请输入数字并填完所有空\n");
					return ;
				}
			}
			//计次
			if(++cntTimes > cntMaxTimes){
				gameOver(false);
				return ;
			}	
			//判断是否正确
			if(Arrays.equals(nums, numInput)){
				gameOver(true);
			}else {
				//不正确，输出判断结果
				calInput();		
			}		
		}
	}
	
	//判断输的结果
	void calInput(){
		int cntA = 0,cntB = 0;
		boolean vis[] = new boolean[]{false,false,false,false};		
		for(int i = 0 ; i < 4 ; ++i){
			if(numInput[i] == nums[i]){	
				vis[i] = true;
				++cntA;				
			}
		}
		
		for(int i = 0 ; i < 4 ; ++i){
			if(numInput[i] != nums[i]){
				for(int j = 0 ; j < 4 ; ++j){
					if(vis[j] == false && numInput[i] == nums[j]){
						++cntB;
						vis[j] = true;
					}
				}
			}
		}
		String str = textResult.getText() + cntTimes + "\t";
		for(int i = 0 ; i < 4 ; ++i){
			str += numInput[i];
		}
		str += "\t" + cntA + "A" + cntB + "B\n";
		textResult.setText(str);		
	}

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
		panelSouth.add(labelResult);
		panelSouth.add(labelTime);	
		
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
        
        buttonNewGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniGame();
			}});
        
        buttonQuit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
                
        buttonInput.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				judgeGame();
			}});
        
        		
		//限制4个编辑框只能输出数字
		for( JTextField t:textInputs ){
			t.addKeyListener(new KeyListener(){
				@Override
				public void keyPressed(KeyEvent arg0) {
					//按 BACK-SPACE 键，如果当前编辑框为空，那么就退回到上一个编辑框，发送shift+tab 消息回去
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE && t != textInputs[0] && t.getText().equals("")){								
						KeyEvent ke = new KeyEvent(t,KeyEvent.KEY_PRESSED,0,1,KeyEvent.VK_TAB,KeyEvent.CHAR_UNDEFINED);
						t.dispatchEvent(ke);
					}else if( arg0.getKeyChar() == KeyEvent.VK_ENTER ){
						judgeGame();
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {}
				@Override
				public void keyTyped(KeyEvent arg0) {
					//这段代码不能放在 keyPressed 上，否则无法在编辑框上显示
					if(Character.isDigit(arg0.getKeyChar()) == false){	
						arg0.consume();
					}else {		
						t.setText("");
						//没输入一次，切换到下一格
						if(t != textInputs[textInputs.length - 1]){
							KeyEvent ke = new KeyEvent(t,KeyEvent.KEY_PRESSED,0,0,KeyEvent.VK_TAB,KeyEvent.CHAR_UNDEFINED);
							t.dispatchEvent(ke);											
						}
					}
				}				
			});
		}
		
		//设置控件属性
		textResult.setFocusable(false);
		buttonNewGame.setFocusable(false);
		buttonQuit.setFocusable(false);
		labelResult.setFocusable(false);
		labelTime.setFocusable(false);
		labelInput.setFocusable(false);
		buttonInput.setFocusable(false);
		
		textResult.setEditable(false);
		setSize(500,500);
		setVisible(true);	
		//编辑框获取焦点
		timer.scheduleAtFixedRate(task, 0, delay);
		iniGame();
	}
	
	class QhyTimerTask extends TimerTask{
		@Override
		public void run() {
			if(isCountTime){
				labelTime.setText("时间:\t" + ++cntTime + "\ts");
			}
		}
	}
	
	public static void main(String[] args) {
		new BullsAndCows();
	}
	

}
