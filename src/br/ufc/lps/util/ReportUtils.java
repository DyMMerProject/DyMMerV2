package br.ufc.lps.util;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
 
/**
 * Classe com m�todos utilit�rios para executar e abrir relat�rios.
 *
 * @author David Buzatto
 */
public class ReportUtils {
 
    /**
     * Abre um relat�rio usando uma conex�o como datasource.
     *
     * @param titulo T�tulo usado na janela do relat�rio.
     * @param inputStream InputStream que cont�m o relat�rio.
     * @param parametros Par�metros utilizados pelo relat�rio.
     * @param conexao Conex�o utilizada para a execu��o da query.
     * @throws JRException Caso ocorra algum problema na execu��o do relat�rio
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao ) throws JRException {
 
        /*
         * Cria um JasperPrint, que � a vers�o preenchida do relat�rio,
         * usando uma conex�o.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );
 
        // abre o JasperPrint em um JFrame
        viewReportFrame( titulo, print );
 
    }
 
    /**
     * Abre um relat�rio usando um datasource gen�rico.
     *
     * @param titulo T�tulo usado na janela do relat�rio.
     * @param inputStream InputStream que cont�m o relat�rio.
     * @param parametros Par�metros utilizados pelo relat�rio.
     * @param dataSource Datasource a ser utilizado pelo relat�rio.
     * @throws JRException Caso ocorra algum problema na execu��o do relat�rio
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource ) throws JRException {
 
        /*
         * Cria um JasperPrint, que � a vers�o preenchida do relat�rio,
         * usando um datasource gen�rico.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource );
 
        // abre o JasperPrint em um JFrame
        viewReportFrame( titulo, print );
 
    }
 
    /**
     * Cria um JFrame para exibir o relat�rio representado pelo JasperPrint.
     *
     * @param titulo T�tulo do JFrame.
     * @param print JasperPrint do relat�rio.
     */
    private static void viewReportFrame( String titulo, JasperPrint print ) {
 
        /*
         * Cria um JRViewer para exibir o relat�rio.
         * Um JRViewer � uma JPanel.
         */
        JRViewer viewer = new JRViewer( print );
 
        // cria o JFrame
        JFrame frameRelatorio = new JFrame( titulo );
 
        // adiciona o JRViewer no JFrame
        frameRelatorio.add( viewer, BorderLayout.CENTER );
 
        // configura o tamanho padr�o do JFrame
        frameRelatorio.setSize( 500, 500 );
 
        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
 
        // configura a opera��o padr�o quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
 
        // exibe o JFrame
        frameRelatorio.setVisible( true );
 
    }
 
}

	