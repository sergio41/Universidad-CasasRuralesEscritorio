package businessLogic;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;


public final class BookManager {

	private int bookNumber = 0;
	dataAccess.DB4oManager dbMngr;

	private static BookManager theBookManager;

	private BookManager() {
	}
	
	/**
	 * This method returns the next Book number 
	 * 
	 * @return the book number
	 */
    public static int getNumber(){
		ObjectContainer db=DB4oManager.getContainer();

    	BookManager b=getInstance();
    	b.bookNumber++;
    	db.store(b);
    	db.commit();
    	return b.bookNumber;
    }
	
	/**
	 * This method returns the instance of a BookManager class 
	 * 
	 * @return the book manager
	 */
	public static BookManager getInstance()  {
		ObjectContainer db=DB4oManager.getContainer();
	    BookManager b = new BookManager();
	    ObjectSet<BookManager> result = db.queryByExample(b);
	    if (!result.hasNext()){
	    	theBookManager = new BookManager();
	    	db.store(theBookManager);
	    	db.commit();
	    } else theBookManager=result.next();
		return theBookManager;
	}
}