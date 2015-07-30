package com.horsegaming.cubex.core.variables.gameobjects;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.activites.parameters.GameType;
import com.horsegaming.cubex.core.variables.base.BoxColorType;
import com.horsegaming.cubex.core.variables.base.Mesh;
import com.horsegaming.cubex.core.variables.base.Transform;

import java.util.ArrayList;

/**
 * Created by Horse on 23.07.2015.
 */
public final class MatrixGameObject extends BaseGameObject<MatrixComponent>
{
    public final static String MATRIX_STANDART_TAG = "MatrixGameObject";

    private SubGameObject[][] _subObjects;

    private Point elementSize ;

    public MatrixGameObject(Transform transform, Mesh texture, int countSubElement)
    {
        super(transform, texture);
        this._subObjects = new SubGameObject[countSubElement][countSubElement];
        this.elementSize = getElementSize();
    }

    //Sub Game Objects control:
    public SubGameObject getSubElement(int x, int y)
    {
        return
        this._subObjects[x][y];
    }

    public int getLength()
    {
        return this._subObjects.length;
    }

    private Point getElementSize()
    {
        return new Point(this.Parameters.Size.x/
                _subObjects.length,this.Parameters.Size.y/_subObjects.length);


    }

    public void moveSubElement(int oldX, int oldY, int newX, int newY, boolean ifEmpty)
    {
        if(ifEmpty && this._subObjects[newX][newY] != null)
            return;

        this.moveSubElement(oldX, oldY, newX, newY);
    }
    public void moveSubElement(int oldX, int oldY, int newX, int newY)
    {
        this._subObjects[newX][newY] = this._subObjects[oldX][oldY];
        this._subObjects[newX][newY].setMatrixPosition(newX, newY);

        for (MatrixComponent c : this._components)
            c.onElementMove(this._subObjects[newX][newY]);
    }
    public void addSubElement(Mesh texture, BoxColorType type, int x, int y, boolean ifEmpty )
    {
        if(ifEmpty && this._subObjects[x][y] != null)
            return;
        this.addSubElement(texture,type, x, y);
    }
    public void addSubElement(Mesh texture, BoxColorType type,  int x, int y)
    {
        this._subObjects[x][y]
                = new SubGameObject(new Transform(x*elementSize.x, y*elementSize.y ,elementSize.x, elementSize.y), texture, this, type);
        this._subObjects[x][y].setMatrixPosition(x, y);
        for (MatrixComponent c : this._components)
            c.onElementCreate(this._subObjects[x][y]);
    }
    public boolean isEmpty(int x, int y)
    {
        return
                this._subObjects[x][y] == null;
    }
    public boolean dropSubElement(int x, int y)
    {
        if(!this.isEmpty(x+1,y) && x + 1 >= this._subObjects.length)
            return false;

        this.moveSubElement(x,y,x+1,y);
        return true;
    }
    public void destroySubElement(int x, int y)
    {
        for (MatrixComponent c : this._components)
            c.onElementDestroy(this._subObjects[x][y]);
        for(SubComponent sc : this._subObjects[x][y]._components)
            sc.onDestroy();
        this._subObjects[x][y] = null;
    }
    public SubGameObject contains(Point position)
    {
        int x = (position.x-this.Parameters.Position.x)/this.getElementSize().x;
        int y = (position.y-this.Parameters.Position.y)/this.getElementSize().y;

        if(x < 0 || y < 0 || x > this._subObjects.length || y > this._subObjects.length ) return null;

        return this._subObjects[x][y];
    }
    public void addSubComponentToAll(SubComponent component)
    {
        for (int i = 0; i < this._subObjects.length; i++)
        {
            for (int j = 0; j < this._subObjects.length; j++)
            {
                this._subObjects[i][j].addComponent(component);
            }
        }
    }

    //Override
    @Override
    public void drawGameObject(Canvas canvas)
    {
        this.Texture.draw(canvas, Parameters);
        if(_subObjects == null) return;

        for (int i = 0; i < _subObjects.length; i++)
        {
            for (int j = 0; j < _subObjects.length; j++)
            {
               this._subObjects[i][j].localDraw(canvas);
            }
        }
    }
    @Override
    public void onClickGameObject(Point clickPoss) {
        super.onClickGameObject(clickPoss);
        SubGameObject sgo =  this.contains(clickPoss);
        if(sgo != null)
            for(SubComponent c : sgo._components)
                c.onClick(clickPoss);


    }
    @Override
    public boolean addComponent(MatrixComponent component)
    {
        if(super.addComponent(component))
        {
            this._components.get(_components.size()).GameObject = this;
            return true;
        }else
        {
            return false;
        }

    }

    public final class SubGameObject implements IComponentable<SubComponent>
    {
        ArrayList<SubComponent> _components = new ArrayList<>();

        public boolean isMarked = false;
        public boolean isDestroy = false;

        public BoxColorType Type;

        private Point _matrixPosition;
        Transform parameters;

        public MatrixGameObject Parent;
        public Mesh Texture;

        SubGameObject(Transform transform, Mesh texture, MatrixGameObject parent, BoxColorType type)
        {
            this.parameters = transform;
            this.Texture = texture;
            this.Parent = parent;
            this.Type = type;
        }

        void localDraw( Canvas canvas)
        {
            this.Texture
                    .draw(canvas,this.parameters);
        }
        void setMatrixPosition(int x, int y)
        {
            this._matrixPosition
                    = new Point(x,y);
        }


        public Point getMatrixPosition()
        {
            return
                    this._matrixPosition;
        }
        public void setLocalPosition(Point position)
        {
            this.parameters.Position
                    = position;
        }
        public Point getLocalPosition()
        {
            return
                    this.parameters.Position;
        }
        public boolean inPosition()
        {
            return (this.parameters.Position.x == this._matrixPosition.x * this.parameters.Size.x &&
                    this.parameters.Position.y == this._matrixPosition.y * this.parameters.Size.y);
        }

        @Override
        public SubComponent getComponent(Class<SubComponent> component) {
            for(SubComponent c : this._components)
                if(c.getClass() == component)
                    return c;
            return null;
        }
        @Override
        public SubComponent getComponent(String name) {
            for(SubComponent c : this._components)
                if(c.getClass().toString() == name)
                    return c;
            return null;
        }
        @Override
        public boolean addComponent(SubComponent component) {
            if(!this._components.contains(component))
            {
                this._components.add(component);
                this._components.get(this._components.size()).OnStart();
                this._components.get(this._components.size()).subGameObject = this;
                return true;
            }else
            {
                Log.d("Missing add", "GameObjject have" + component.getClass().toString());
                return false;
            }
        }
        @Override
        public void removeComponent(String name) {
            for(SubComponent c : this._components)
                if(c.getClass().toString() == name) {
                    c.onDestroy();
                    this._components.remove(c);
                    return;
                }
        }

    }
}
