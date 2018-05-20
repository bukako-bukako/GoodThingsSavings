import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //-----------------
            // �ڑ�
            //-----------------
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", // "jdbc:postgresql://[�ꏊ(Domain)]:[�|�[�g�ԍ�]/[DB��]"
                    "postgres", // ���O�C�����[��
                    "1235"); // �p�X���[�h
            statement = connection.createStatement();

            //-----------------
            // SQL�̔��s
            //-----------------
            //���[�U�[���̃e�[�u��
            resultSet = statement.executeQuery("SELECT * FROM pg_shadow");

            //-----------------
            // �l�̎擾
            //-----------------
            // �t�B�[���h�ꗗ���擾
            List<String> fields = new ArrayList<String>();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                fields.add(rsmd.getColumnName(i));
            }

            //���ʂ̏o��
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;

                System.out.println("---------------------------------------------------");
                System.out.println("--- Rows:" + rowCount);
                System.out.println("---------------------------------------------------");

                //�l�́A�uresultSet.getString(<�t�B�[���h��>)�v�Ŏ擾����B
                for (String field : fields) {
                    System.out.println(field + ":" + resultSet.getString(field));
                }
            }


        } finally {
            //�ڑ���ؒf����
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}