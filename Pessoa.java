public class Pessoa {

    private String NomeCompleto;
    private String Endereco;
    private String Telefone;
    private String CPF;
    private String TipoSanguineo;
    private String fatorRH;
    private String Curso;
    private String ContatoEmergencia;
    private String TelefoneEmergencia;
    private double Peso;
    private double Altura;
    private double IMC;
    private int id;

    public Pessoa(String NomeCompleto, String Endereco, String Telefone, String CPF, String TipoSanguineo, String fatorRH, String Curso, String TelefoneEmergencia, String ContatoEmergencia, double peso, double altura) {
        this.NomeCompleto = NomeCompleto;
        this.Endereco = Endereco;
        this.Telefone = Telefone;
        this.CPF = CPF;
        this.TipoSanguineo = TipoSanguineo;
        this.fatorRH = fatorRH;
        this.Curso = Curso;
        this.TelefoneEmergencia = TelefoneEmergencia;
        this.ContatoEmergencia = ContatoEmergencia;
        this.Peso = peso;
        this.Altura = altura;
        this.IMC = calcularIMC(); // Calcula o IMC no construtor
    }

    public String getNome() {
        return NomeCompleto;
    }

    public String getEndereco() {
        return Endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTipoSanguineo() {
        return TipoSanguineo;
    }

    public String getfatorRH() {
        return fatorRH;
    }

    public String getCurso() {
        return Curso;
    }

    public String getContatoEmergencia() {
        return ContatoEmergencia;
    }

    public String getTelefoneEmergencia() {
        return TelefoneEmergencia;
    }

    public double getPeso() {
        return Peso;
    }

    public double getAltura() {
        return Altura;
    }

    public double getIMC() {
        return IMC;
    }

    public double calcularIMC() {
        return Peso / (Altura * Altura);
    }

    public String classificarIMC() {
        double imc = calcularIMC();
        if (imc < 18.5) {
            return "Você está abaixo do peso ideal";
        } else if (imc >= 18.5 && imc <= 25) {
            return "Peso Ideal";
        } else {
            return "Você está acima do peso ideal!";
        }
    }
}