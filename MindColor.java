package MindColor;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JFrame; // 프레임 그리는거
import javax.swing.JLabel; // gui 문자
import javax.swing.JPanel; // 프레임 위 표

public class MindColor implements Runnable 
{

    private JFrame frame;

    @Override
    public void run() 
    {
        initGUI();
    }

    public void initGUI() 
    {
        frame = new JFrame("오늘의 마음");
        frame.setSize(600, 600); // 프레임 크기 설정
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // 프레임 중앙에 올 수 있도록
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫으면 종료한대
        
        frame.add(Calendar()); // 달력 판넬 추가

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel Calendar() 
    { // 1년 365일짜리 모눈 캘린더 만들어주는거

        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(32, 12));

        for (int day = 0; day < 32; day++) 
        {
        	if (day > 0) 
            {
                panel.add(new JLabel(Integer.toString(day))); // 일 버튼 추가
            } 
            else 
            {
                panel.add(new JLabel(" "));
            }
            
            for (int mon = 0; mon < 12; mon++) 
            {
                if (day == 0) 
                {
                    panel.add(new JLabel(Integer.toString(mon + 1)));
                } 
                else 
                {
                	FeelColor feel = new FeelColor();
                	feel.addMouseListener(new ColorListener(feel));
                    panel.add(feel); // 월 버튼 추가
                }
            }
        }

        return panel;
    }

    public class FeelColor extends JPanel { // 픽셀 한 칸을 색칠해주는 역할

        private static final long serialVersionUID = 8465814529701152253L; // 자바 직렬화시킴 클래스 버전이 맞는지 확인하려고 그럼..

        private static final int PIXEL_SIZE = 20;

        private Color backgroundColor; // 어차피 하얀색
        
        // 각각 기분의 색깔 알려줌
        Color Exited = new Color(102, 153, 204);
    	Color Good = new Color(153, 204, 255);
    	Color Normal = new Color(255, 255, 204);
    	Color Nervous = new Color(255, 204, 204);
    	Color Frustrated = new Color(255, 153, 153);
    	
        public FeelColor() {
            this.backgroundColor = Color.WHITE; // 배경색 하양
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 선 색은 검정
            this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE)); // 20*20 짜리 픽셀임
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        @Override
        protected void paintComponent(Graphics g) { // 픽셀 색칠해주는 함수
            super.paintComponent(g);

            g.setColor(getBackgroundColor());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public class ColorListener extends MouseAdapter { // 마우스로 클릭하면 색깔 바꾸는 클래스

        private FeelColor feel; // 색깔 바꾸려고
        int cnt = 0; // 색깔 바꾸려고

        public ColorListener(FeelColor feel) {
            this.feel = feel;
        }

        @Override
        public void mousePressed(MouseEvent event) { // 마우스 믈릭해서 색깔 변하게 하는 함수
            if (event.getButton() == MouseEvent.BUTTON1) { // 픽셀을 왼쪽 마우스로 누르면 색 변경
            	if(cnt == 0) // 한번 누르면 행복
            	{
            		feel.setBackgroundColor(new Color(102, 153, 204)); // 색 바뀔때마다 하얀색으로 다시 칠하고 바꾸어야 색 혼선이 없음
            		feel.repaint();
            		cnt++;
            	}
            	else if(cnt == 1) // 두번 누르면 좋음
            	{
            		feel.setBackgroundColor(Color.WHITE);
                	feel.repaint();
            		feel.setBackgroundColor(new Color(153, 204, 255));
            		feel.repaint();
            		cnt++;
            	}
            	else if(cnt == 2) // 세번 누르면 평범
            	{
            		feel.setBackgroundColor(Color.WHITE);
                	feel.repaint();
            		feel.setBackgroundColor(new Color(255, 255, 204));
            		feel.repaint();
            		cnt++;
            	}
            	else if(cnt == 3) //4번 누르면 우울
            	{
            		feel.setBackgroundColor(Color.WHITE);
                	feel.repaint();
            		feel.setBackgroundColor(new Color(255, 204, 204));
            		feel.repaint();
            		cnt++;
            	}
            	else if(cnt == 4) //5면 누름면 화남, 거기서 한번 더 누르면 초기화
            	{
            		feel.setBackgroundColor(Color.WHITE); 
                	feel.repaint();
            		feel.setBackgroundColor(new Color(255, 153, 153));
            		feel.repaint();
            		cnt = 0;
            	}
            } else if (event.getButton() == MouseEvent.BUTTON3) { // 잘못 클릭하면 오른쪽 마우스 눌러서 초기화해줌
            	feel.setBackgroundColor(Color.WHITE);
            	feel.repaint();
            	if( cnt > 1)
            	{
            		cnt -= 2; // 3번 이상 클릭하면 그 전것으로 초기화
            	}
            	else
            	{
            		cnt--; // 3번 미만 클릭하면 바로 전 색으로 초기화
            	}
            }
        }
    }

    public static void main(String[] args) // main
    {
        EventQueue.invokeLater(new MindColor()); // 자바 스윙 이벤트 처리하는거
    }
}
