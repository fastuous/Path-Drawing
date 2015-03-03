package com.trumandeyoung.pathdrawing;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;

public class PathDrawing extends ApplicationAdapter {
    int samplePoints;
    float samplePointDistance;

    SpriteBatch batch;
    BitmapFont font;
    ImmediateModeRenderer renderer;
    List<Vector2> points;
    Path<Vector2> path;
    final Vector2 tmpV = new Vector2();
    int delay;

    @Override
    public void create() {
        renderer = new ImmediateModeRenderer20(false, true, 0);
        points = new LinkedList<Vector2>();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale(2f);
        delay = 0;
    }

    @Override
    public void render() {
        delay++;

        if (Gdx.input.justTouched()) {
            points.add(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));

            if (delay < 20) points.removeAll(points);

            delay = 0;
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glLineWidth(2.5f);

        if (points.size() > 0) {
            samplePoints = points.size() * 20;
            samplePointDistance = 1f / samplePoints;

            Vector2 pointArray[] = new Vector2[points.size()];
            pointArray = points.toArray(pointArray);
            path = new CatmullRomSpline<Vector2>(pointArray, true);

            renderer.begin(batch.getProjectionMatrix(), GL20.GL_LINE_STRIP);
            float val = 0f;
            while (val <= 1f) {
                renderer.color(0f, 0f, 0f, 1f);
                path.valueAt(tmpV, val);
                renderer.vertex(tmpV.x, tmpV.y, 0);
                val += samplePointDistance;
            }
            renderer.end();
            String info = "Number of vertices: " + points.size();

            batch.begin();
            font.draw(batch, info, 0, Gdx.graphics.getHeight());
            batch.end();
        }

    }
}