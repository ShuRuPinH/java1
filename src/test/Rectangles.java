package test;

import java.applet.*;
import java.awt.*;


public class Rectangles extends Applet {

    DrawRectangles m_DrawRectThread = null;
    DrawEllipse m_DrawEllipseThread = null;
    NotifyTask m_NotifyTaskThread = null;

    public String getAppletInfo() {
        return "Name: Rectangles";
    }

    public void paint(Graphics g) {
        Dimension dimAppWndDimension = getSize();
        g.setColor(Color.yellow);
        g.fillRect(0, 0,
                dimAppWndDimension.width - 1,
                dimAppWndDimension.height - 1);
        g.setColor(Color.black);
        g.drawRect(0, 0,
                dimAppWndDimension.width - 1,
                dimAppWndDimension.height - 1);
    }

    public void start() {
        if (m_DrawRectThread == null) {
            m_DrawRectThread =
                    new DrawRectangles(this);
            m_DrawRectThread.start();
        }
        if (m_DrawEllipseThread == null) {
            m_DrawEllipseThread =
                    new DrawEllipse(this);
            m_DrawEllipseThread.start();
        }
        if (m_NotifyTaskThread == null) {
            m_NotifyTaskThread =
                    new NotifyTask(m_DrawEllipseThread);
            m_NotifyTaskThread.start();
        }
    }

    public void stop() {
        if (m_DrawRectThread != null) {
            m_DrawRectThread.stop();
            m_DrawRectThread = null;
        }
        if (m_DrawEllipseThread == null) {
            m_DrawEllipseThread.stop();
            m_DrawEllipseThread = null;
        }
        if (m_NotifyTaskThread != null) {
            m_NotifyTaskThread.stop();
            m_NotifyTaskThread = null;
        }
    }

    public static void main(String[] args) {
        Rectangles rect = new Rectangles();
        rect.start();
    }
}

class DrawRectangles extends Thread {
    Graphics g;
    Dimension dimAppWndDimension;

    public DrawRectangles(Applet Appl) {
        g = Appl.getGraphics();
        dimAppWndDimension = Appl.getSize();
    }

    public void run() {
        while (true) {
            int x, y, width, height;
            int rColor, gColor, bColor;
            x = (int) (dimAppWndDimension.width
                    * Math.random());
            y = (int) (dimAppWndDimension.height
                    * Math.random());
            width = (int) (dimAppWndDimension.width
                    * Math.random()) / 2;
            height = (int) (dimAppWndDimension.height
                    * Math.random()) / 2;
            rColor = (int) (255 * Math.random());
            gColor = (int) (255 * Math.random());
            bColor = (int) (255);
            g.setColor(new Color(rColor,
                    gColor, bColor));
            g.fillRect(x, y, width, height);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                stop();
            }
        }
    }
}

class DrawEllipse extends Thread {
    Graphics g;
    Dimension dimAppWndDimension;

    public DrawEllipse(Applet Appl) {
        g = Appl.getGraphics();
        dimAppWndDimension = Appl.getSize();
    }

    public synchronized void run() {
        while (true) {
            int x, y, width, height;
            int rColor, gColor, bColor;
            x = (int) (dimAppWndDimension.width
                    * Math.random());
            y = (int) (dimAppWndDimension.height
                    * Math.random());
            width = (int) (dimAppWndDimension.width
                    * Math.random()) / 2;
            height = (int) (dimAppWndDimension.height
                    * Math.random()) / 2;
            rColor = (int) (255 * Math.random());
            gColor = (int) (255 * Math.random());
            bColor = (int) (255);
            g.setColor(new Color(rColor,
                    gColor, bColor));
            g.fillOval(x, y, width, height);
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
    }
}

class NotifyTask extends Thread {
    Thread STask;

    public NotifyTask(Thread SynchroTask) {
        STask = SynchroTask;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
            }
            synchronized (STask) {
                STask.notify();
            }
        }
    }
}
