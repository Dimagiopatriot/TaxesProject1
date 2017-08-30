package model.dao.exceptions;

public class DaoException extends Exception {

    public DaoException(){
        super();
    }

    DaoException(String msg){
        super(msg);
    }
}
