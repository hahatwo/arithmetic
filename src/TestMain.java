public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "GklvKvqYhGEMvhwNoAzAsxPRbhSizddZdKjrDE"
				+ "dGWykxmBCrVasurDQdleokwcpKFPQSNxzyzdmDEpksncKsgigFfLLHKlShehruoU"
				+ "nNpvAauasokVvgNgZpOjdzpGmsJpxtnEeqfoNyknndYPQErfGkZedhcdwplfqjekP"
				+ "XbolByOmyinuuqYSgGfaLOxWczusKWLfcsUoakTlVtsqfNQKbGnrdVkHqGgVoRUcsVytDre"
				+ "djSmDMcxxPaMHbwOLmKvWUrxcoUtkUcYtbBoCqbLOaMCFbtmwKeJnrMEWenAVMWQsobLENzgkSm"
				+ "WrxMtdHbZLXixstfdDzWXhdUbScskssozOEMKhtjJXiRziiBATyQKPzSKFfWauqzxlZCutCvQteL"
				+ "gmostYIYxpyplNRvedLmppzvIzLtkMOqvgLTErvSrRggIzuVvMCwZkujflaoakvGdZQimnTqaiMSko"
				+ "CPpzcpAEdwuYJrFHDEnUIuywXvArbLbaycCkzzfRsFdCgrKuPmEvapdwgZSVloJaLIbkqhvk"
				+ "wcefgfTaphsXzuFozPBpFwHyjlvktiBEgbgvmoHzzJycgOtBDpnpxglBbwXhggnjBblIcsHgJY";
		char[] chars = str.toCharArray();
		SortLettersbyCase slcase = new SortLettersbyCase();
		slcase.sortLetters(chars);
		str = String.valueOf(chars);
		System.out.println(str);
		
		
	}
}
