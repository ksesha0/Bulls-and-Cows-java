# Bulls and Cows 猜数字游戏
游戏规则：xAyB 表示有x个数字的位置是对的，y个数字位置不对，但是数字对
![show](https://raw.githubusercontent.com/285571052/Bulls-and-Cows-java/master/show.png)

# 知识点
## GUI 布局

一般布局：
   * 使用panel 和 GridBagLayout 把窗口分成指定大小
   * 再在 panel 里面布置控件
   
GridBagLayout 的简单使用
```
GridBagLayout gbLayout= new GridBagLayout();							
setControlPos(0,0,0,1,0,panelNorth,gbLayout);									
setLayout(gbLayout);
void setControlPos(int gridx,int gridy,int gridwidth,double weightx,double weighty, Component comp ,GridBagLayout gbLayout ){
	GridBagConstraints gbConstraint= new GridBagConstraints();
	gbConstraint.fill =  GridBagConstraints.BOTH;
	gbConstraint.gridx = gridx;//设置comp的左上角的位置
	gbConstraint.gridy = gridy;
	gbConstraint.weightx = weightx;//控件长度是否往右延伸
	gbConstraint.weighty = weighty;//控件高度是否往下延伸
	gbConstraint.gridwidth = gridwidth;//控件的宽度,值为0的时候，表示是本行最后一个控件
	gbLayout.setConstraints(comp, gbConstraint);
}
```		

## java.util.Timer 的简单使用

## GUI 添加监听器
```
控件.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
});
```

## GUI 传递事件
下面的参数 1 表示 按下 shift ，这个值本人是通过 pressed 事件输出得到的，在文档中查找是 MASK_SHIFT??
```
//传递 shift + tab 事件
KeyEvent ke = new KeyEvent(控件,KeyEvent.KEY_PRESSED,0,1,KeyEvent.VK_TAB,KeyEvent.CHAR_UNDEFINED);
控件.dispatchEvent(ke);
```

## keyTyped 和 pressed 事件的小区别？
观察所得，原理还没学习.
keyTyped 事件能在编辑框中输入
pressed  事件则不能
相应了pressed 事件，那么keyTyped 事件貌似没产生
