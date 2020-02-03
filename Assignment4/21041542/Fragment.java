	/*
	 * Copyright 2017 Marc Liberatore.
	 */
	
	package sequencer;
	
	public class Fragment {
		private String fragment ;
		
		/**
		 * Creates a new Fragment based upon a String representing a sequence of nucleotides, 
		 * containing only the uppercase characters G, C, A and T.
		 * @param nucleotides
		 * @throws IllegalArgumentException if invalid characters are in the sequence of nucleotides 
		 */
		public Fragment(String nucleotides) throws IllegalArgumentException {
			boolean res = false;
			for(char ch : nucleotides.toCharArray()) {
				if(ch == 'G' || ch == 'C'||ch == 'A'||ch == 'T'){
					res = true;
				}
				else {
					res = false;
					break;
				}
			}
			if(res == true) {
				fragment = nucleotides;
			}else {
				throw new IllegalArgumentException("Invaild character");
			}
		}
		
		/**
		 * Returns the length of this fragment.
		 * 
		 * @return the length of this fragment
		 */
		public int length() {
			return fragment.length();
		}
		
		/**
		 * Returns a String representation of this fragment, exactly as was passed to the constructor.
		 * 
		 * @return a String representation of this fragment
		 */
		@Override
		public String toString() {
			return fragment;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fragment == null) ? 0 : fragment.hashCode());
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
			Fragment other = (Fragment) obj;
			if (fragment == null) {
				if (other.fragment != null)
					return false;
			} else if (!fragment.equals(other.fragment))
				return false;
			return true;
		}
		
		/**
		 * Returns the number of nucleotides of overlap between the end of this fragment and
		 * the start of another fragment, f.
		 * 
		 * The largest overlap is found, for example, CAA and AAG have an overlap of 2, not 1.
		 * 
		 * @param f the other fragment
		 * @return the number of nucleotides of overlap
		 */
		//Failed with testSequenceHaemoglobinSubunitBeta. Rewrite
		/**
		public int calculateOverlap(Fragment f) {
			int i = f.length();
			int count = 0;
			if(fragment.length() < f.toString().length()) {
				i = fragment.length();
			}
			for(int n = 0;n <= i;n++) {
				//String wdnmd = fragment.toString().substring(i - n,i);
				//String wsnd = f.toString().substring(0, n);
				if(fragment.toString().substring(i - n,i).equals(f.toString().substring(0, n))){
					count = n;
				}
			}
			return count;
		}
		*/
		public int calculateOverlap(Fragment f) {
			int count = 0;
			int res = 0;
			if(fragment.equals(f.toString())) {
				return fragment.length();
			}
			if (f.length() > fragment.length()) {
				for (int i = 0; i < fragment.length(); i++) {
					if(f.toString().substring(0,i).equals(fragment.substring(fragment.length() - i))) {
						res = fragment.substring(fragment.length() - i).length();
					}
					count++;
				}
			} else {
				for (int i = 0; i < f.length(); i++) {
					if(f.toString().substring(0,i).equals(fragment.substring(fragment.length() - i))) {
						res = fragment.substring(fragment.length() - i).length();
					}
					count++;
				}
			}
			return res;
		}
		
		/**
		 * Returns a new fragment based upon merging this fragment with another fragment f.
		 * 
		 * This fragment will be on the left, the other fragment will be on the right; the
		 * fragments will be overlapped as much as possible during the merge.
		 *  
		 * @param f the other fragment
		 * @return a new fragment based upon merging this fragment with another fragment 
		 */
		public Fragment mergedWith(Fragment f) {
			Fragment c = new Fragment(fragment);
			//Fragment res = new Fragment("");
			int calc = c.calculateOverlap(f);
			/**
			if(calc == 0) {
				Fragment empty = new Fragment("");
				return empty;
			}else if(calc == c.toString().length()) {
				return c;
			}
			*/
			Fragment res = new Fragment(c.toString().substring(0, c.toString().length() - calc) + f.toString());
			//res = c.toString().subSequence(0, calc + 1) + f.toString().substring(calc, f.length());
			return res;
			
		}
	}
