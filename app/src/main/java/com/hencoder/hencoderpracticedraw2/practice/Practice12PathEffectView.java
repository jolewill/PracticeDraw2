package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect


        //将mpath封闭，也可以写 mpath.lineTo(100, 100);代替

        //canvas.drawPath(path1,paint);

        // 第一处：CornerPathEffect
        PathEffect pathEffect = new CornerPathEffect(20);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.save();
        canvas.translate(500, 0);
        pathEffect = new DiscretePathEffect(10, 20);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        pathEffect = new DashPathEffect(new float[]{10, 20}, 10);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect

        Path path1 = new Path();
        path1.moveTo(10, 10);
        //从起始位置划线到(200, 200)坐标
        path1.lineTo(20, 20);
        path1.lineTo(0,20);
        path1.close();
        pathEffect = new PathDashPathEffect(path1, 10, 5,
                PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        PathEffect pathEffect1 = new DashPathEffect(new float[]{10,20},20);
        PathEffect pathEffect2 = new DiscretePathEffect(10,20);
       // PathEffect pathEffect3 = new SumPathEffect(pathEffect1,pathEffect2);
        PathEffect pathEffect4 = new SumPathEffect(pathEffect1,pathEffect2);
        paint.setPathEffect(pathEffect4);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        pathEffect = new ComposePathEffect(pathEffect,pathEffect1);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
