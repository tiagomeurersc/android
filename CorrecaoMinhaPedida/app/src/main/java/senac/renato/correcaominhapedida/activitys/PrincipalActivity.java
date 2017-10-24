package senac.renato.correcaominhapedida.activitys;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import senac.renato.correcaominhapedida.R;
import senac.renato.correcaominhapedida.modelos.MyAdapter;
import senac.renato.correcaominhapedida.modelos.Pedido;
import senac.renato.correcaominhapedida.modelos.Produto;


public class PrincipalActivity extends AppCompatActivity {

    ListView lvPedidos;
    ArrayList<Pedido> listPedidos;
    MyAdapter myAdapter;
    TextView tvTotalPagar;
    double total = 0;
    TextToSpeech tts;

    Dao<Pedido, Integer> pedidoDao;
    Dao<Produto, Integer> produtoDao;

    Pedido pedido = null;

    final int REQUEST_QR_CODE = 10;
    final int REQUEST_LEITURA_VOZ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

//        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void OnInit(int status){
//                if (status != TextToSpeech.ERROR){
//                    tts.setLanguage(Locale.getDefault());
//                }
//            }
//
//        });

        tvTotalPagar = (TextView) findViewById(R.id.tvTotalPagar);

        lvPedidos = (ListView) findViewById(R.id.lvPedidos);

        listPedidos = new ArrayList<>();

        myAdapter = new MyAdapter(listPedidos, this);

        lvPedidos.setAdapter(myAdapter);

        lvPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Pedido pedido = (Pedido) myAdapter.getItem(i);

                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Opções clique longo");
                alerta.setMessage(pedido.getItemPedido().toString());

                alerta.setPositiveButton("+1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pedido.addMais1();
                        myAdapter.notifyDataSetChanged();

                        total += pedido.getItemPedido().getValor();
                        atualizarTotal();
                    }
                });

                alerta.setNegativeButton("-1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            if(pedido.getQuantidade()>1) {
                                pedido.removMenos1();
                                myAdapter.notifyDataSetChanged();

                                total -= pedido.getItemPedido().getValor();
                                atualizarTotal();
                            }
                    }
                });

                alerta.setNeutralButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myAdapter.remover(pedido);
                        myAdapter.notifyDataSetChanged();

                        total -= pedido.getSubtotal();
                        atualizarTotal();
                    }
                });

                alerta.show();

                return true;
            }
        });

    }

//    public void falarTexto(View v){
//        EditText editNome = (EditText) findViewById(R.id.editTextoFalar);
//        tts.stop();
//        tts.speak(editNome.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
//    }

    public void chamarTela(View v){
        Intent it = new Intent(this, AddProdutoActivity.class);
        startActivityForResult(it, 99);
    }

    public void chamarQrCode (View v){
        try {
            Intent intent =  new Intent ("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_QR_CODE);
        } catch (ActivityNotFoundException anfe){
            Toast.makeText(this, "Baixe o QR CODE", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    public void leituraVoz (View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE. "pt-BR");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale um texto");
        startActivityForResult(intent, REQUEST_LEITURA_VOZ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        pedido = new Pedido();

        if(resultCode==RESULT_OK) if (requestCode == REQUEST_QR_CODE) {
            Toast.makeText(this, "QrCode:\n" + data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_SHORT).show();

        } else if (requestCode == 99) {
            Bundle param = data.getExtras();
            Pedido pedido = (Pedido) param.getSerializable("pedido");

            myAdapter.add(pedido);
            myAdapter.notifyDataSetChanged();
            total += pedido.getSubtotal();
            atualizarTotal();
       }
        try {
            Dao.CreateOrUpdateStatus res = pedidoDao.createOrUpdate(pedido);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void atualizarTotal(){
        tvTotalPagar.setText("R$"+total);
    }
}
