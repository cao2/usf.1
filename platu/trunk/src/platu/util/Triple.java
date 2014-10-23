package platu.util;

public class Triple<T1, T2, T3> {
	T1 first;
	T2 middle;
	T3 last;
	
	public Triple(T1 t1, T2 t2, T3 t3) {
		this.first = t1;
		this.middle = t2;
		this.last = t3;
	}

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getMiddle() {
		return middle;
	}

	public void setMiddle(T2 middle) {
		this.middle = middle;
	}

	public T3 getLast() {
		return last;
	}

	public void setLast(T3 last) {
		this.last = last;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((middle == null) ? 0 : middle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triple other = (Triple) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (this.first != other.first)
			return false;
		
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (this.last != other.last)
			return false;
		
		if (middle == null) {
			if (other.middle != null)
				return false;
		} else if (this.middle != other.middle)
			return false;
		
		return true;
	}
	
	

}
