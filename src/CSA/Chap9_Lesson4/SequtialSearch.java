package CSA.Chap9_Lesson4;

public class SequtialSearch {

	
	public int search(int val,int[] datas) {
		int pos = -1;
		for(int i =0;i<datas.length;i++) {
			if(val == datas[i]) {
				return i;
			}
		}
		return pos;
	}
	public void selectionSort(int[] x) {
		for(int m=0;m<x.length;m++) {
			for(int n=m+1 ;n<x.length;n++) {
				if(x[m]>x[n]) {
					int temp = x[m];
					x[m] = x[n];
					x[n] = temp;
				}
			}
		}
	}
	public void selectionSort1(int[] x) {
		for(int m=0;m<x.length;m++) {
			int index = m;
			for(int n=m+1 ;n<x.length;n++) {
				if(x[index]>x[n]) {
					index = n;
				}
			}
			if(index != m) {
				int temp = x[m];
				x[m] = x[index];
				x[index] = temp;
			}
		}
	}
	public static void main(String[] args) {
		int[] data = {23,4,2,9,30,5};
		SequtialSearch ss = new SequtialSearch();
		int pos = ss.search(10, data);
		System.out.println(pos);
	}

}
