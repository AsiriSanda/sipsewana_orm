package dao;

import entity.SuperEntity;

import java.io.Serializable;
import java.util.ArrayList;

public interface SuperDAO<Entity extends SuperEntity,ID extends Serializable> {
    boolean save(Entity entity)throws Exception;
    boolean update(Entity entity)throws Exception;
    boolean delete(ID id)throws Exception;
    Entity search(ID id)throws Exception;
    ArrayList<Entity> getAll()throws Exception;
}
