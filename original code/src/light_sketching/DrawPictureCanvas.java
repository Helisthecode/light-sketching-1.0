//虚拟画板
package light_sketching;

import java.awt.*;


public class DrawPictureCanvas extends Canvas{

    public static Image image=null;        //创建画板中展示的图片对象
    public void setImage(Image image){
        this.image=image;
    }
    public DrawPictureCanvas() {
        // 设置背景色，便于观察
        setBackground(Color.gray);
    }
    /*
    重写paint（）方法，在画布上绘制图像
     */
    @Override
    public void paint(Graphics g){

        int width = getWidth();
        int height = getHeight();
        DrawPictureFrame.canvasWidth=width;
        DrawPictureFrame.canvasHeight=height;
        //System.out.println(width);
        //g.setColor(Color.WHITE);
        if (DrawPictureFrame.clearCanvas){
        g.clearRect(0,0,getWidth(),getHeight());
            if (DrawPictureFrame.CNtext){
                g.drawString("点击\"新建\"按钮以新建一个 "+String.valueOf(width)+"x"+String.valueOf(height)+" 的画布",
                        width/2-150,height/2);}else{
                g.drawString("click the \"new\" button to establish a "+String.valueOf(width)+"x"+String.valueOf(height)+" canvas",
                        width/2-150,height/2);

            }

        }
        //掩盖drawstring的文字
        if (DrawPictureFrame.count2==1){
            g.setColor(Color.gray);
            g.fillRect(0,0,width,height);
            DrawPictureFrame.count2++;
        }

        if (DrawPictureFrame.count==1){
            g.drawImage(image,0,0,this);
//            for (Graphics2D shape:DrawPictureFrame.shapes)
        }

    }

    /**
     * 重写update（）方法，解决屏幕闪烁
     */
    @Override
    public void update(Graphics g){
        paint(g);
    }
}
