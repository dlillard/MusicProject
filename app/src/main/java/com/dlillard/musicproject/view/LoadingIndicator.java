package com.dlillard.musicproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created by dlillard on 5/20/15.
 */
public class LoadingIndicator extends View {
    private Paint paint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF bounds;

    public LoadingIndicator(Context c){
        super(c);
    }

    protected void onSizeChanged (int w, int h, int oldw, int oldh){
        float diameter = Math.min(w, h);
        System.out.println(w + " : " + h);
        bounds = new RectF(0,0,diameter,diameter);
    }

    protected void onDraw (Canvas canvas){
        float w=getWidth(), h=getHeight();
        float frame=AnimationUtils.currentAnimationTimeMillis()/100;
        canvas.drawArc(0,0,w,h,0,frame%360,true,paint);
        paint.setARGB(255,255,255,255);
        canvas.drawArc(0,0,w,h,0,frame%360,true,paint);

        invalidate();
    }
}
