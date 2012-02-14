import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.AbstractListModel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 12, 2012.
 * @param <E> 
 */
public class FilteredListModel<E> extends AbstractListModel {
	private Iterable<E> original;
	private ArrayList<E> filtered;
	private String filter;

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param original
	 * @param filter
	 */
	public FilteredListModel(Iterable<E> original) {
		super();
		this.original = original;
		this.filter = "";
		this.filtered = new ArrayList<E>();
		this.update();
	}

	@Override
	public E getElementAt(int index) {
		return this.filtered.get(index);
	}

	@Override
	public int getSize() {
		return this.filtered.size();
	}

	/**
	 * Returns the value of the field called 'filter'.
	 * @return Returns the filter.
	 */
	public String getFilter() {
		return this.filter;
	}

	/**
	 * Sets the field called 'filter' to the given value.
	 * @param filter The filter to set.
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void update() {
		this.filtered.clear();
		for (E e : this.original) {
			if (Pattern.compile(Pattern.quote(this.filter), Pattern.CASE_INSENSITIVE).matcher(e.toString()).find()) {
				this.filtered.add(e);
			}
		}
		fireContentsChanged(this, 0, getSize());
	}

}
