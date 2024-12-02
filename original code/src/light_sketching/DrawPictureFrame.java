//1.0
package light_sketching;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import com.mr.util.FrameGetShape;
import com.mr.util.ShapeWindow;
import com.mr.util.Shapes;
import com.mr.util.DrawImageUtil;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * 画图主窗体
 */
public class DrawPictureFrame extends JFrame implements FrameGetShape{      //继承窗体类
    static BufferedImage newimage2;
    public static int count=0;
    public static boolean clearCanvas=true;
    public static boolean CNtext=false;
    public static int count2=0 ;
    //创建一个8位BGR颜色分量的图像
    static int canvasWidth=100;//最大宽：1639
    static int canvasHeight=100;//最大高：881
    DrawPictureCanvas canvas=new DrawPictureCanvas();//创建画布对象

    Color forecolor=Color.BLACK;//定义前景色(画笔颜色)
    Color backgroundColor=Color.WHITE;//定义背景色（画布颜色）
    int x=-1;//上一次鼠标绘制点的横坐标
    int y=-1;//上一次鼠标绘制点的纵坐标
    boolean rubber=false;//橡皮擦
    private JToolBar toolBar;//工具栏
    private JToggleButton eraserButton;//橡皮按钮
    private JToggleButton strokeButton1;//细线按钮
    private JToggleButton strokeButton2;//粗线1按钮
    private JToggleButton strokeButton3;//粗线2按钮
    private JButton backgroundButton;//背景色按钮
    private JButton foregroundButton;//前景色按钮
    private JButton clearButton;
    private JButton shapeButton;//图形按钮
    private JButton newButton;//新建按钮
    //private JButton shrinkButton;//缩小按钮
    boolean drawShape=false;//画图形
    Shapes shape;//绘画的图形
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem ENMenuItem;
    private JMenuItem CNMenuItem;
    //private Stack<Graphics2D> shapes=new Stack<>();

    /**
     * 构造方法，添加组件监听方法
     */
    public DrawPictureFrame(){
        setResizable(true);       //can't change the size of window
        setTitle("light sketching");
        ImageIcon icon = new ImageIcon("D:\\java_space\\project_2\\src\\light_sketching\\sss.png");
        Image image = icon.getImage();
        setIconImage(image);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关窗口即结束
        setBounds(500,100,800,650);    //location and length-width of window
        init();//组件初始化
        //添加组件监听

    }


    /**
     * 组件初始化，给窗体添加工具栏及工具栏上的按钮
     */
    private void init(){
        getContentPane().add(canvas);//将画布添加到窗体容器默认布局的中部位置
        toolBar=new JToolBar();//
        getContentPane().add(toolBar,BorderLayout.NORTH);//工具栏添加到窗体最北的位置
        //初始化按钮对象，并添加文本内容
        newButton =new JButton("new");
        toolBar.add(newButton);
        toolBar.addSeparator();

        strokeButton1=new JToggleButton("thin line");
        strokeButton1.setSelected(true);//
        toolBar.add(strokeButton1);//
        //初始化按钮对象，并添加文本内容
        strokeButton2=new JToggleButton("thick line");
        toolBar.add(strokeButton2);//
        //初始化有选中状态的按钮对象，并添加文本内容
        strokeButton3=new JToggleButton("Coarser");
        eraserButton=new JToggleButton("eraser");

        //按钮组，保证同时只有一个按钮被选中
        ButtonGroup strokeGroup=new ButtonGroup();
        strokeGroup.add(strokeButton1);
        strokeGroup.add(strokeButton2);
        strokeGroup.add(strokeButton3);
        strokeGroup.add(eraserButton);

        toolBar.add(strokeButton3);
        toolBar.add(eraserButton);
        toolBar.addSeparator();
        backgroundButton=new JButton("background color");
        toolBar.add(backgroundButton);
        foregroundButton=new JButton("foreground color");
        toolBar.add(foregroundButton);
        toolBar.addSeparator();
            //shrinkButton=new JButton("shrink");
            //toolBar.add(shrinkButton);
        shapeButton=new JButton("shape");
        toolBar.add(shapeButton);
        toolBar.addSeparator();
        clearButton=new JButton("clear");
        toolBar.add(clearButton);

        JMenuBar menuBar=new JMenuBar();//创建菜单栏
        setJMenuBar(menuBar);//窗体载入菜单栏
        JMenu systemMenu=new JMenu("system");//菜单对象
        menuBar.add(systemMenu);
        systemMenu.addSeparator();
        openMenuItem=new JMenuItem("open");//菜单项对象
        systemMenu.add(openMenuItem);
        saveMenuItem=new JMenuItem("save");
        systemMenu.add(saveMenuItem);
        exitMenuItem=new JMenuItem("exit");
        systemMenu.add(exitMenuItem);
        JMenu languagesMenu=new JMenu("languages");
        menuBar.add(languagesMenu);
        ENMenuItem=new JMenuItem("EN");
        languagesMenu.add(ENMenuItem);
        CNMenuItem=new JMenuItem("中文");
        languagesMenu.add(CNMenuItem);

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                openImage();
                clearCanvas=false;
                count=1;

                Graphics gs= newimage2.getGraphics();//获得图像的绘图对象
                Graphics2D g=(Graphics2D) gs;   //绘图对象转为Graphics2D类型

                g.setColor(forecolor);//用前景色设置绘图对象的颜色
                canvas.setImage(newimage2);//设置画布的图像
                count2++;
                canvas.repaint();
                addListener(g,newimage2);
            }
        });

        ENMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraserButton.setText("eraser");
                strokeButton1.setText("thin line");
                strokeButton2.setText("thick line");
                strokeButton3.setText("Coarser");
                backgroundButton.setText("background color");
                foregroundButton.setText("foreground color");
                clearButton.setText("clear");
                shapeButton.setText("shape");
                newButton.setText("new");
                systemMenu.setText("system");
                languagesMenu.setText("languages");
                saveMenuItem.setText("save");
                openMenuItem.setText("open");
                exitMenuItem.setText("exit");
                ENMenuItem.setText("EN");
                CNMenuItem.setText("中文");
                CNtext=false;
                canvas.repaint();
            }
        });
        CNMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraserButton.setText("橡皮擦");
                strokeButton1.setText("细线");
                strokeButton2.setText("粗线1");
                strokeButton3.setText("粗线2");
                backgroundButton.setText("背景颜色");
                foregroundButton.setText("画笔颜色");
                clearButton.setText("清除");
                shapeButton.setText("形状");
                newButton.setText("新建");
                systemMenu.setText("系统");
                languagesMenu.setText("语言");
                saveMenuItem.setText("保存");
                openMenuItem.setText("打开");
                exitMenuItem.setText("退出");
                ENMenuItem.setText("EN");
                CNMenuItem.setText("中文");
                CNtext=true;
                canvas.repaint();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCanvas=false;

                count=1;
                BufferedImage image=new BufferedImage(canvasWidth,canvasHeight,BufferedImage.TYPE_INT_BGR);//图像尺寸
                Graphics gs= image.getGraphics();//获得图像的绘图对象
                Graphics2D g=(Graphics2D) gs;   //绘图对象转为Graphics2D类型
                g.setColor(backgroundColor);//用背景色设置绘图对象的颜色，g是绘图对象（2D）
                g.fillRect(0,0,canvasWidth,canvasHeight);//用背景色填充整个画布
                g.setColor(forecolor);//用前景色设置绘图对象的颜色
                canvas.setImage(image);//设置画布的图像
                canvas.repaint();
                addListener(g,image);

            }
        });
    }

    /**
     * 为组件添加动作监听
     */

    private void addListener(Graphics2D g,BufferedImage image){
        //画板添加鼠标移动事件监听

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged (final MouseEvent e) {//当鼠标拖拽时

                if (x>0&&y>0&&!drawShape){//如果x和y存在鼠标记录
                    if (rubber){//如果橡皮为true，表示使用橡皮
                        g.setColor(backgroundColor);//
                        g.fillRect(x,y,10,10);//在鼠标滑过的位置画直线
                    }else {//如果橡皮为false，表示用画笔画图
                        g.setColor(forecolor);
                        g.drawLine(x,y,e.getX(),e.getY());
                        //shapes.push(g);
                        //鼠标滑过的位置画直线

                    }
                }
                x=e.getX();//上一次鼠标绘制点的横坐标
                y=e.getY();//上一次鼠标绘制点的纵坐标
                canvas.repaint();//更新画布
            }//mouseDragged()  end
            @Override
            public void mouseMoved(final MouseEvent arg0){
                if (rubber){
                    //设置鼠标指针的形状为图片
                    Toolkit kit=Toolkit.getDefaultToolkit();//获得系统默认的组件工具包
                    //利用工具包获取图片
                    Image img=kit.createImage("D:\\java_space\\project_2\\src\\light_sketching\\rubbericon.png");
                    //利用工具包创建一个自定义的光标对象(cursor)
                    Cursor c=kit.createCustomCursor(img,new Point(0,0),"clear");//参数：（图片，光标热点，描述字符串）
                    setCursor(c);//启用
                }else {
                    //十字光标
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }//mouseMoved()  end
        });//canvas.addMouseMotionListener()  end
        //鼠标移动到工具栏时恢复正常的光标
        toolBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(final MouseEvent arg0) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //画板添加鼠标单击事件监听
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent arg0) {//当按键抬起时
                x=-1;//将记录上一次鼠标绘制点的横坐标恢复为-1
                y=-1;//将记录上一次鼠标绘制点的纵坐标恢复为-1
                drawShape=false;
            }
            public void mousePressed(MouseEvent e){//当按键按下时
                x=e.getX();//上一次鼠标绘制点的横坐标
                y=e.getY();//上一次鼠标绘制点的纵坐标
                if (x>0&&y>0&&!drawShape){//如果x和y存在鼠标记录
                    g.setColor(forecolor);
                    g.drawLine(x,y,e.getX(),e.getY());//鼠标滑过的位置画直线
                }

                canvas.repaint();//更新画布

                if (drawShape){
                    switch (shape.getType()){
                        case Shapes.YUAN://如果是圆形
                            //计算坐标，让鼠标处于圆形中心位置
                            int yuanX=e.getX()-shape.getWidth()/2;
                            int yuanY=e.getY()-shape.getHeigth()/2;
                            //创建圆形图形，并指定坐标和宽高
                            Ellipse2D yuan=new Ellipse2D.Double(yuanX,yuanY,shape.getWidth(),shape.getHeigth());
                            g.draw(yuan);
                            break;
                        case Shapes.FANG://如果是方形
                            int fanX=e.getX()-shape.getWidth()/2;
                            int fanY=e.getY()-shape.getHeigth()/2;
                            Rectangle2D fang=new Rectangle2D.Double(fanX,fanY,shape.getWidth(),shape.getHeigth());
                            g.draw(fang);
                            break;
                    }//switch()  end
                    canvas.repaint();

                }


            }//mousePressed()  end
        });//canvas.addMouseListener()  end
        strokeButton1.addActionListener(new ActionListener() {//“细线”(thin line)按钮添加动作监听
            @Override
            public void actionPerformed(final ActionEvent arg0) {//单击时
                rubber=false;
                BasicStroke bs=new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
                g.setStroke(bs);

            }
        });//strokeButton1.addActionListener()  end
        strokeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                rubber=false;
                BasicStroke bs=new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
                g.setStroke(bs);

            }
        });//strokeButton2.addActionListener()  end
        strokeButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                rubber=false;
                BasicStroke bs=new BasicStroke(4,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
                g.setStroke(bs);

            }
        });//strokeButton3.addActionListener()  end
        backgroundButton.addActionListener(new ActionListener() {//背景颜色(background color)按钮的动作监听
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                // 打开颜色选择对话框
                Color bgcolor=JColorChooser.showDialog(DrawPictureFrame.this,"color chooser",Color.WHITE);
                if (bgcolor!=null){
                    backgroundColor=bgcolor;
                }
                g.setColor(backgroundColor);
                g.fillRect(0,0,canvasWidth,canvasHeight);
                g.setColor(forecolor);
                canvas.repaint();
            }
        });//BackgroundButton.addActionListener()  end
        foregroundButton.addActionListener(new ActionListener() {//画笔颜色(foreground color)按钮的动作监听
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                Color fcolor=JColorChooser.showDialog(DrawPictureFrame.this,"color chooser",Color.RED);
                if (fcolor!=null){
                    forecolor=fcolor;
                }
                foregroundButton.setForeground(forecolor);//画笔颜色（foreground color）按钮的颜色
                g.setColor(forecolor);
            }
        });//foregroundButton.addActionListener()  end
        clearButton.addActionListener(new ActionListener() {//清除（clear）按钮的动作监听
            @Override
            public void actionPerformed(final ActionEvent arg0) {
            g.setColor(backgroundColor);
            g.fillRect(0,0,canvasWidth,canvasHeight);
            g.setColor(forecolor);
            canvas.repaint();
            }
        });//clearButton.addActionListener()  end
        eraserButton.addActionListener(new ActionListener() {//橡皮擦（eraser）按钮的动作监听
            @Override
            public void actionPerformed(final ActionEvent arg0) {
            rubber=true;
            }
        });//eraserButton.addActionListener()  end
        shapeButton.addActionListener(new ActionListener() {//图形（shape）按钮的动作监听
            @Override
            public void actionPerformed(ActionEvent e) {//单击时
                //创建图形选择组件
                ShapeWindow shapeWindow=new ShapeWindow(DrawPictureFrame.this);
                int shapeButtonWidth=shapeButton.getWidth();//获取图形按钮宽度
                int shapeWindowWidth=shapeWindow.getWidth();//获取图形按钮高度
                int shapeButtonX=shapeButton.getX();//获取图形按钮横坐标
                int shapeButtonY=shapeButton.getY();//获取图形按钮纵坐标
                //计算图形组件横坐标，让组件与“图形”按钮居中对齐
                int shapeWindowX=getX()+shapeButtonX-(shapeWindowWidth-shapeButtonWidth)/2;
                //计算图形组件纵坐标，让组件始终位于“图形”按钮下方
                int shapeWindowY=getY()+shapeButtonY+80;
                //组件位置
                shapeWindow.setLocation(shapeWindowX,shapeWindowY);
                shapeWindow.setVisible(true);//使组件可见
            }
        });//shapeButton.addActionListener()  end

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });//exitMenuItem.addActionListener()  end
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawImageUtil.saveImage(DrawPictureFrame.this,image);//打印图片

            }
        });

//                shrinkButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
    }
    public void  openImage(){
        JFileChooser fileChooser=new JFileChooser();
        int result=fileChooser.showOpenDialog(null);
        File sfile=fileChooser.getSelectedFile();
        try{
            //File file=new File("src/icon/example.jpg");
            this.newimage2=ImageIO.read(sfile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * FrameGetShape接口实现类，用于获得图形空间返回的被选中的图形
     */
    public void getShape(Shapes shape){
        this.shape=shape;//将返回的图形对象赋给类的全局变量
        drawShape=true;
    }

    /**
     * 程序运行主方法
     * @param args
     */
    public static void main(String[] args){
        DrawPictureFrame frame=new DrawPictureFrame();
        frame.setVisible(true);
        System.out.println();

    }


}
