package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class CircularStrategy implements Strategy{
    private int locationX;
    private int locationY;
    private int shootNum;
    private int speed=5;
    private int power;
    public CircularStrategy(){}


    public List<BaseBullet> shoot(AbstractAircraft aircraft)
    {        locationX=aircraft.getLocationX();
        locationY=aircraft.getLocationY();
        power=aircraft.getPower();
        shootNum=aircraft.getShootnum();
        List<BaseBullet> res = new LinkedList<>();
        int x = this.locationX;
        int y = this.locationY;//+ direction*20;
        int speedx;
        int speedy;
        BaseBullet bullet;
        double[] angle = new double[shootNum];

        for (int i = 0; i < shootNum; i++) {
            angle[i] = i / (double) (shootNum) * 2 * Math.PI;
        }
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            speedx = (int) (speed * Math.cos(angle[i]));
            speedy = (int) (speed * Math.sin(angle[i]));

            if(aircraft instanceof AbstractEnemy)
                bullet = new EnemyBullet(x , y, speedx, speedy, power);
            else
            { bullet = new HeroBullet(x , y, speedx, speedy, power);}
            res.add(bullet);
        }
        return res;
    }
}