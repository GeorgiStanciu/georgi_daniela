package com.example.georgi.shop.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.example.georgi.shop.Adapters.ProductAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.Product;
import com.example.georgi.shop.Models.ReviewModel;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity {


    private GridView gridView;
    private  ArrayList<Product> products;

    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main,null);
        gridView = (GridView) view.findViewById(R.id.grid_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Client client =  new Client();
                client.connectToServer();
                CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.ViewProductsCommand));
                products = (ArrayList<Product>) response.getResponse();
                client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setProducts();
                    }
                });
            }
        }).start();


        contentLayout.addView(view);

    }

    private void setProducts(){
        ProductAdapter adapter = new ProductAdapter(this, products);
        gridView.setAdapter(adapter);

    }


    private void populateProducts(){
        UserModel user = new UserModel("email", "1", "Georgi");
        ArrayList<ReviewModel> reviews = new ArrayList<>();
        ArrayList<String> images;
        Calendar calendar = Calendar.getInstance();
        Date utilDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        /*reviews.add(new ReviewModel(1, "excelent","Cel mai bun produs ever!!!!!", user, date, 5));
        reviews.add(new ReviewModel(2, "excelent","Un produs reussit", user, date, 5));
        reviews.add(new ReviewModel(3, "prost","Un produs mai prost de atat nu am vazut niciodata.....niciodata", user, date, 1));
        reviews.add(new ReviewModel(4, "dezamagitor","Ma asteptam la mai mult cu asemenea specificatii", user, date, 2));
        reviews.add(new ReviewModel(5, "se putea si mai bine","Merge, dar....", user, date, 4));
        reviews.add(new ReviewModel(6, "dragut, dar nu prea","", user, date, 2));
        reviews.add(new ReviewModel(7, "excelent","Sunt foate multumita de acest produs. Mi-a schimbat viata radical", user, date, 5));
        reviews.add(new ReviewModel(8, "alta intrebare?","Deci?", user, date, 4));
        reviews.add(new ReviewModel(9, "asa si asa","Eeeeee, oricum in vara imi iau altul", user, date, 3));*/


        //Laptop,Tablete,Telefoane

            //Laptop si accesorii

                 //Laptop
       /* images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_e3aa490ff1062047b57a6a7a390fd55a_450x450_6dgt.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_77729d6b6b964f2c48594b8ba819d6fa_full.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_71a422b6d75aef41d1dd5ce3ff9545a6_450x450_rvr5.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_de3fc42584f675f47fb8b56745d3a6bd_450x450_4bkq.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_8dd379b097e24df71827927215cde99a_450x450_ppg3.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_a5abaabbe5ad6b0fe59452d275676b76_450x450_b47l.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_c91bc19926b35773112885a65b465078_450x450_r9iu.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_98f5a2c1fbf08604ac8f03e7e566147d_450x450_t9qe.jpg");
        images.add("https://s5emagst.akamaized.net/products/4141/4140183/images/res_6e75432b089311efd7ed1f0f801ff7d3_450x450_66nn.jpg");

        products.add(new Product(1, "Laptop ASUS X550VX-XX017D", " Procesor Intel® Core™ i7-6700HQ 2.60GHz, Skylake™, 15.6\", 8GB, 256GB SSD, nVIDIA GeForce GTX 950M 2GB, Free DOS",
                images, "Laptop", 4249.99f, 30, "Shop", 24, 21, null));

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_3a83f8272f26ec540a88dfa793433611_450x450_bovb.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_1db05558a11e8452620bf30b39ad5fc9_450x450_c458.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_fa8c0561479f2d595afc3f6f29ee32bb_450x450_qmep.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_936df95eb114c361bef10d968523e697_450x450_4856.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_225d7d3d0c883f86280bd3f9a14f26a6_450x450_heee.jpg");
        images.add("https://s5emagst.akamaized.net/products/4714/4713031/images/res_6a422db87a46b91deb827ba08e07701d_450x450_12vm.jpg");

        products.add(new Product(2, "Laptop ASUS X540SA-XX311", " Procesor Intel® Celeron® N3060 1.60GHz, Braswell, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 400, Free DOS, Chocolate Black",
                images, "Laptop", 1666.0f, 36, "Biasicom", 24, 4, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/res_49e87252f4e6ede1b47eadc2aee9010b_450x450_5efn.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_2_450x450_ivj2.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_3_450x450_cek4.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140414_4_450x450_1cpd.jpg");
        images.add("https://s5emagst.akamaized.net/products/3445/3444790/images/img301171_18062013140413_1_full.jpg");

        products.add(new Product(3, "Laptop Apple MacBook Air 13", " Procesor Intel® Dual Core™ i5 1.60GHz, 13.3\", 8GB, 128GB SSD, Intel® HD Graphics 6000, OS X El Capitan, RO KB",
                images, "Laptop", 5399.0f, 16, "Shop", 24, 40, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_6406644063198bf893accc1b2f71b694_450x450_qi87.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_728caf0dc991cd01079160abe128af4b_450x450_5kac.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_77fdcc4b35713ad8eb7cfc6e0af35041_450x450_tu76.jpg");
        images.add("https://s5emagst.akamaized.net/products/3961/3960267/images/res_1b65075e0d3708f6a96dfb0b073776eb_450x450_tqc1.jpg");

        products.add(new Product(4, "Laptop HP 250 G5", " Procesor Intel® Core ™ i3-5005U 2.00GHz, Broadwell™, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 5500, Free DOS, Black",
                images, "Laptop", 1699.99f, 0, "Shop", 24, 0, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_56f5c18cc2b6a45d293b6c4a24d5e4a9_450x450_mj2u.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_f78a9381197197abf6a4d7dfd12488ef_450x450_fv8i.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_ffb300eaee2c0314d0d14b1d882abdec_450x450_dod6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_b28a94c0bdb74bd6bbe615acf7dfcc91_450x450_riqi.jpg");
        images.add("https://s5emagst.akamaized.net/products/5000/4999103/images/res_87c9671ff94bee432c1a4bbbee029d1a_450x450_nl2n.jpg");

        products.add(new Product(5, "Laptop DELL 15.6'' Vostro 3568 (seria 3000), HD", " Procesor Intel® Core™ i3-6100U (3M Cache, 2.30 GHz), 4GB DDR4, 1TB, GMA HD 520, Linux, Black",
                images, "Laptop", 1990.99f, 0, "Neoplaza", 24, 4, null));







        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_4db21274133ffa1316bc6c4ac9e1f462_full.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_1e252e276225542d59a1a9bdf197819c_450x450_j5ti.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_a9f9b7a7228897be8009b3f0148f8f75_450x450_ur1b.jpg");
        images.add("https://s5emagst.akamaized.net/products/5013/5012589/images/res_8458a5c2d74243f4aa9b7926c0bc9195_450x450_qcaf.jpg");

        products.add(new Product(6, "Laptop 2in1 Odys Fusion 12", "11.6 inch IPS LED FHD, 4Core Intel Atom X5 1.44 GHz, 2GB + 32GB, Wi-Fi, Bluetooth, Micro HDMI, Windows 10 Home, Tastatura, Negru (Include: Tastatura tip Dock)",
                images, "Laptop", 1399.0f, 21, "Atu IT", 24, 0, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2b43c469c6887c15da92248e20f2421b_450x450_pgh6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_12a3ebbe33a33bd567b628ed24772b7d_450x450_fbu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2e66698fd6d7830c1a35e7b77be4e358_450x450_7jvm.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_c1c90de37fa55ce61dfa0559a7ef0b12_450x450_seu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_46d953466630dcbc0359deccd5ebdc83_450x450_ttcr.jpg");

        products.add(new Product(7, "Laptop Gaming Acer Aspire F5-573G-707G", "Procesor  Intel® Core™ i7-7500U 2.70 GHz, Kaby Lake™, 15.6\", Full HD, 8GB, 256GB SSD, DVD-RW, nVIDIA GeForce GTX 950M 4GB, Linux, Silver",
                images, "Laptop", 4742.39f, 36, "Neoplaza", 24, 14, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_4bb700667298951f03d3b1f029de2c42_450x450_5s3k.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_c2c593d8999c374cee3f03019d01b7b9_450x450_b6dr.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_93c5c5bc209d1098ff39940ec173c2e7_450x450_h2k8.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_7b1dffc94275a5d5c227a1e99ee67e6a_450x450_sbag.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_d106281ae2eff5f8a2e6052338574590_450x450_hs6g.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_db6c8245331a46d30012e68effde5228_450x450_2ejb.jpg");
        images.add("https://s5emagst.akamaized.net/products/5151/5150650/images/res_15f2c675bd9cabc92198f2b44660f828_450x450_dh7e.jpg");

        products.add(new Product(8, "Laptop Lenovo IdeaPad 710S P-13IKB", " Procesor Intel® Core™ i5-7200U 2.50 GHz Kaby Lake™, 13.3\" Full HD IPS, 8GB, 512GB SSD, nVIDIA® GeForce® 940M 2GB,Microsoft Windows 10 Home, Silver",
                images, "Laptop", 4699.99f, 14, "Shop", 24, 26, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_7e8c99504ed6044f83bba808bf3b4cda_450x450_l5o4.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_10071b8bf822e860ad104036c7f1ecdb_450x450_n0aj.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_9ade8b90316578a104dfb04255d8334e_450x450_308.jpg");
        images.add("https://s5emagst.akamaized.net/products/4746/4745221/images/res_dc58d1499aadd4efb044b5606de7f116_450x450_de6o.jpg");

        products.add(new Product(9, "Ultrabook Dell XPS 13 9360", " Procesor Intel® Core™ i7-7500U 2.70GHz, Kaby Lake™, 13.3\", FHD, 8GB, 256GB SSD, Intel® HD Graphics 620, Microsoft Windows 10 Home, Silver",
                images, "Laptop", 8330.000f, 0, "Alien Store", 24, 45, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_05a1217973a2b4517b95874159aab378_450x450_82aj.jpg");
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_47b24f5f54619556b9d936cec239ec7e_450x450_euko.jpg");
        images.add("https://s5emagst.akamaized.net/products/3446/3445289/images/res_23dc5d62d107a2fcbfa212ccc6dd8963_450x450_2r63.jpg");

        products.add(new Product(10, "Laptop Apple MacBook 12", " Procesor  Intel® Dual Core™ M5 1.20GHz, 12\", Ecran Retina, 8GB, 512GB SSD, Intel® HD Graphics 515, OS X El Capitan, RO KB, Rose Gold",
                images, "Laptop", 8399.0f, 10, "Shop", 24, 13, null));



        //Accesorii laptop

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/69/68269/images/img292807_02052013093734_1_450x450_rlaj.jpg");
        images.add("https://s5emagst.akamaized.net/products/69/68269/images/img292807_02052013093736_3_450x450_58tm.jpg");
        images.add("https://s5emagst.akamaized.net/products/69/68269/images/img292807_02052013093737_4_450x450_pjak.jpg");
        images.add("https://s5emagst.akamaized.net/products/69/68269/images/img292807_02052013093738_5_450x450_blp4.jpg");

        products.add(new Product(11, "Geanta laptop ASUS Nereus", " Combatibil cu laptopuri cu diagonala de pana la 16 inch. Geanta este fabricata dintr-un material rezistent la umiditate, protejand laptopul dumneavoastra de eventuale accidente. Compartiment pentru documente A4. Cureaua de umar este detasabila si se ajusteaza in functie de nevoile dumneavoastra.",
                images, "Accesorii Laptop", 79.99f, 0, "Shop", 12, 45, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/492/491793/images/res_59a331622b5bef564a5731ba067e256a_450x450_afij.jpg");
        images.add("https://s5emagst.akamaized.net/products/492/491793/images/res_1619b5d8d8da94e179032d794de564ac_450x450_6pfr.jpg");
        images.add("https://s5emagst.akamaized.net/products/492/491793/images/res_73dc3494850fadb67a7afcf2e00de993_450x450_8bgb.jpg");

        products.add(new Product(12, "Rucsac Laptop Hama Phuket pentru 15.6\", Black", "Pentru laptopuri de 15.6\", Black",
                images, "Accesorii Laptop", 89.99f, 33, "Shop", 12, 7, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3864/3863160/images/res_56e097ca18b92a507156120c8fd8957e_full.jpg");

        products.add(new Product(13, "Husa laptop HP 13.3\"", "Invelesti laptopul intr-o husa placuta, colorata. Materialul din neopren durabil te ajuta sa protejezi PC-ul impotriva intemperiilor, socurilor si zgarieturilor. Lesne de inversat, pentru a schimba culorile in functie de starea de spirit. Designul reversibil iti permite sa schimbi culoarea oricand doresti. In plus, gratie inchiderii elegante, fara fermoar, laptopul se bucura de o imbracaminte fiabila, comoda. Aceasta husa flexibila si durabila, din neopren, te ajuta sa protejezi PC-ul impotriva intemperiilor, socurilor si zgarieturilor. Iei cu incredere laptopul cu tine.",
                images, "Accesorii Laptop", 39.99f, 0, "Shop", 13, 23, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4037/4036396/images/res_f57f146f0abb1c584f45b243362e88d7_450x450_tlr.jpg");


        products.add(new Product(14, "Tastatura Laptop Lenovo", " Tastatura este compatibila cu urmatoarele modele de laptop Lenovo:\n" +
                "\n" +
                "Gama Lenovo G50 series: G50 30, G50 45, G50 70, G50 80 etc.\n" +
                "Gama Lenovo Z50 series:Z50 70, Z50 75 etc.\n" +
                "Gama Lenovo B50 series: B50 30, B50 45, B50 70, B50 30 Touch etc.\n" +
                "Inainte de a plasa comanda, comparati poza tastaturii si conectorul panglica de pe spate pentru a va asigura ca sunt corespunzatoare laptop-ului dvs. Observatie: Forma tastei enter poate sa difere in functie de lot si nu afecteaza cu nimic functionalitatea tastaturii sau a laptop-ului",
                images, "Accesorii Laptop", 10.14f, 22, "Power laptop", 12, 20, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/479/478784/images/res_bdb01a32a1db8c4b564c6233d9d49c4d_450x450_90fg.jpg");

        products.add(new Product(15, "Hard Disk Laptop Toshiba MQ01ABD100", "",
                images, "Accesorii Laptop", 283.995f, 0, "ID DIRECT", 0, 56, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3864/3863160/images/res_56e097ca18b92a507156120c8fd8957e_full.jpg");

        products.add(new Product(16, "Hard Disk Laptop Seagate Mobile 1TB,, 5400rpm, 128MB cache, SATA III", "",
                images, "Accesorii Laptop", 268.85f, 0, "Vexio", 0, 23, null));


        //Telefoane mobile si accesorii
        //Telefoane mobile

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5500/5499271/images/res_11321017c6b73cfa87fa476abe185507_450x450_tm05.jpg");
        images.add("https://s5emagst.akamaized.net/products/5500/5499271/images/res_f939bf8a33e62ff18c6222767f7dbf82_450x450_rtt9.jpg");
        images.add("https://s5emagst.akamaized.net/products/5500/5499271/images/res_fc085c825e5cdd14b29e7fb24f300f7b_450x450_ema.jpg");
        products.add(new Product(17, "Telefon mobil Samsung Galaxy S8 Plus, 64GB, 4G, Orchid Grey", "Designul inovator al telefoanelor Galaxy S8 și S8+ începe din interior. Am regândit fiecare componentă a telefonului pentru a depăşi limitele ecranului unui smartphone. Astfel, vezi doar conținut, fără ramă. Este cel mai mare și mai captivant ecran pentru un dispozitiv mobil cu aceste dimensiuni. În plus, este ușor de ținut în mână. Surprinde viaţa aşa cum este în realitate pe camerele Galaxy S8 și S8+. Camera din spate de 12 MP și cea frontală de 8 MP sunt atât de rapide și precise, încât nu vei pierde niciun moment, fie zi, fie noapte. Îți folosești telefonul aproape tot timpul. De aceea, Galaxy S8 și S8+ au integrat primul procesor de 10 nm din lume. Este rapid, puternic și crește eficiența bateriei. În plus, există posibilitatea de a extinde capacitatea de stocare și îl poţi folosi chiar şi în condiţii de ploaie și praf datorită standardului IP68.",
                images, "Telefoane mobile", 3999.99f, 0, "Shop", 24, 21, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4159/4158442/images/res_bbdfaa9537747e2cd6dd46a657f9107b_450x450_q4h3.jpg");
        images.add("https://s5emagst.akamaized.net/products/4159/4158442/images/res_3b3636ac90d7a9afe016593aeb018473_450x450_t78h.jpg");
        images.add("https://s5emagst.akamaized.net/products/4159/4158442/images/res_736c107919c34f387ab1e0561dd6eaad_450x450_ddvk.jpg");
        images.add("https://s5emagst.akamaized.net/products/4159/4158442/images/res_b5283db759160571c9392798623229ac_450x450_g3ko.jpg");

        products.add(new Product(18, "Telefon mobil Apple iPhone 7, 32GB, Black\n", " iPhone 7 imbunatateste substantial cele mai importante aspecte care definesc experienta iPhone. Iti ofera noi sisteme avansate pentru camere. Cele mai bune performante si cea mai extinsa autonomie din toate timpurile pentru iPhone. Difuzoare stereo captivante. Cea mai mare luminozitate si cea mai bogata gama cromatica pentru un afisaj de iPhone. Rezistenta la stropire si apa. Un design pe masura performantelor. Acesta este iPhone 7.",
                images, "Telefoane mobile", 3499.99f, 5, "Shop", 24, 23, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3697/3696222/images/res_ed0ae56469259ca424fbf5a8efb4d7cb_450x450_l01h.jpg");
        images.add("https://s5emagst.akamaized.net/products/3697/3696222/images/res_d367761bf0e4e663c7753c63500f1f33_450x450_5fqe.jpg");
        images.add("https://s5emagst.akamaized.net/products/3697/3696222/images/res_5c00026c20e3f96dafea2850d52c4e30_450x450_dv6p.jpg");

        products.add(new Product(19, "Telefon mobil Huawei Y3II, Dual Sim, 8GB, 4G, Gold", "Huawei Y3II se aprinde atunci cand primesti un apel, sau cand primiti unele notificari. Lasati-va coplesit de nuantele luminoase care danseaza pe ritmul tonului de apel. Lasati telefonul sa trimita un curcubeu in lumea dumneavoastra. Muzica este singurul lucru care te tine treaz noaptea. Oferind o performanta acustica minunata, Huawei Y3II plaseaza experienta captivanta a unui concert in propiul buzunar.",
                images, "Telefoane mobile", 389.990f, 0, "Shop", 24, 10, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5268/5267501/images/res_3d134a349d9d77dac23584bc5eba6d0a_450x450_jd19.jpg");
        images.add("https://s5emagst.akamaized.net/products/5268/5267501/images/res_3bbffad8e4d9c08c7daebf35d8a0ad12_450x450_vlo6.jpg");

        products.add(new Product(20, "Telefon Mobil Cubot Rainbow 2, Dual SIM 3G", "5\" HD IPS, 4Core 1.3 GHz, 1GB + 16GB, Dual Camera 13+2 MPx, Blit Frontal, LED Notificare (Albastru), Certificat GMS, Auriu +BONUS: Husa Silicon si Boxe Mini-USB Media-Tech",
                images, "Telefoane mobile", 379.00f, 0, "Atu IT", 24, 0, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_7e81788577733c6048cb8868b2b40db3_450x450_ir8i.jpg");
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_77f4cab8e4ae5a0c6a125fc78d5827a8_450x450_2pvl.jpg");
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_942385d09abb30ebda3cde888029aefc_450x450_r6tr.jpg");

        products.add(new Product(21, "Telefon mobil Allview P9 Energy, Dual SIM, 64GB, 4G, Gold", "raim intr-o lume in care azi trebuie sa fim mai buni decat ieri. Iar maine…maine o vom lua de la capat. Intr-o lume a competitiei continue, ai nevoie de toate resursele care fac diferenta. Energia este vitala, iar noi ti-am pregatit doza optima pentru zile lungi, foarte lungi. Descopera P9 Energy, smartphone-ul care nu te lasa la greu.",
                images, "Telefoane mobile", 2149.99f, 20, "Shop", 24, 14, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4929/4928835/images/res_d046046263d24b57ea4ca01ea34e8414_450x450_8pvd.jpg");
        images.add("https://s5emagst.akamaized.net/products/4929/4928835/images/res_2fa6022c76da6804120f435f2e38b130_450x450_f659.jpg");
        images.add("https://s5emagst.akamaized.net/products/4929/4928835/images/res_df82fccebb5f2bd8485c16b198f3cdd9_450x450_vooo.jpg");

        products.add(new Product(22, "Telefon mobil iHunt One Love, Dual SIM", "4G, 5.5-inch FHD, Quad-Core, 3GB RAM, 32GB, Android 6.0, Grey (include Husa Silicon si Folie Protectie)",
                images, "Telefoane mobile", 1099.0f, 38, "iHunt", 24, 26, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2b43c469c6887c15da92248e20f2421b_450x450_pgh6.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_12a3ebbe33a33bd567b628ed24772b7d_450x450_fbu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_2e66698fd6d7830c1a35e7b77be4e358_450x450_7jvm.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_c1c90de37fa55ce61dfa0559a7ef0b12_450x450_seu2.jpg");
        images.add("https://s5emagst.akamaized.net/products/5138/5137126/images/res_46d953466630dcbc0359deccd5ebdc83_450x450_ttcr.jpg");

        products.add(new Product(23, "Telefon Mobil Xiaomi Redmi 4 Pro", "3GB RAM, 32GB ROM, 13MP, Snapdragon, Dual SIM, Gold",
                images, "Telefoane mobile", 979.0f, 8, "BMK", 24, 5, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4600/4599259/images/res_e6ce4bf30c12915b6a37986fbd692354_450x450_7f7a.jpg");
        images.add("https://s5emagst.akamaized.net/products/4600/4599259/images/res_4b6563bed0045d237a2882099b7f3b53_450x450_t0je.jpg");
        images.add("https://s5emagst.akamaized.net/products/4600/4599259/images/res_e86fd335232220accd213df91df8a843_450x450_ts6b.jpg");

        products.add(new Product(24, "Telefon mobil LG G5, H860, Dual Sim, 32GB, 4G, Roz", "",
                images, "Telefoane mobile", 2149.99f, 5, "QuickMobile", 24, 26, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4924/4923769/images/res_63064d9d8157ae4fa7015ef5cae75fff_450x450_p54g.jpg");
        images.add("https://s5emagst.akamaized.net/products/4924/4923769/images/res_f135db1e9b6e1d48ee2bb4fc02a9bae0_450x450_7lrh.jpg");
        images.add("https://s5emagst.akamaized.net/products/4924/4923769/images/res_9b7d5d75b7db200d4ee70d6bd3a390d3_450x450_hraf.jpg");

        products.add(new Product(25, "Telefon mobil LG X Screen, Dual Sim, 16GB, 4G, Negru", "Acest produs provine de la distribuitori oficiali autorizati si este conform cu normele europene. El este livrat in ambalajul original, cu instructiuni in limba engleza/franceza/germana/italiana/spaniola, adaptor gratuit pentru priza europeana si garantie standard 24 luni inclusa asigurata de Service Return\n" +
                "\n Cutia produsului si dispozitivul pot avea instructiuni si aplicatii atat in limba engleza cat si in limba chineza, modelul fiind varianta internationala. Telefonul se poate folosi in orice retea mobila atat din Germania/Anglia/Spania/Franta/Italia cat si din restul lumii, el nefiind niciodata codat.",
                images, "Telefoane mobile", 609.90f, 0, "QuickMobile", 24, 6, null));




        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_9e20681df804c5dc2119b8c1d3b0bcc9_450x450_rarf.jpg");
        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_73bff447fb28cfe34ac85ccad9f6cc9f_450x450_pumi.jpg");
        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_5c632684b966e3b40578eb9470a03b45_450x450_a7kn.jpg");

        products.add(new Product(26, "Laptop Apple MacBook 12", "O imbinare intre metalul solid, de calitate premium, si sticla Gorilla Glass cu aspect atragator. Un model pe care il tineti in mana mai stabil si mai comod, cu un aspect mai ingust si o fateta mai subtire.\n O combinatie eleganta de metal si sticla\n" +
                "O imbinare intre metalul solid, de calitate premium, si sticla Gorilla Glass cu aspect atragator. Un model pe care il tineti in mana mai stabil si mai comod, cu un aspect mai ingust si o fateta mai subtire.\n" +
                "Performanta puternica\n.Bucurati-va de o performanta exceptionala a componentelor hardware si de o viteza de internet ultra-rapida. Procesorul octa-core si accesul la retea LTE Cat.6 permit incarcarea web optima, o tranzitie mai simpla a interfetei si multi-tasking mai rapid.",
                images, "Telefoane mobile", 1749.99f, 0, "Shop", 24, 0, null));




        //Accesorii telefoane


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3477/3476553/images/res_70a5f195718bda56baf8b6b4acdaebae_450x450_kdig.jpg");

        products.add(new Product(27, "Husa de protectie Lemontti Silicon Ultraslim pentru Samsung Galaxy A5 (2016) ,transparent", "usa silicon Ultraslim Lemontti este cea mai subtire protectie din silicon prezenta in momentul de fata in piata interna - numai 0,33 mm. Abia vizibila, aceasta husa transparenta din silicon se muleaza perfect pe formele telefonului acoperind eficient suprafata acestuia, inclusiv butoanele de volum si power; are deschideri la toate porturile.\n" +
                "In ciuda aspectului fragil, husa este realizata dintr-un material de calitate, rezistent la zgarieturi lovituri si uzura. Cu aceasta husa din silicon, smartphone-ul tau va fi protejat fara ca acest lucru sa fie vizibil.",
                images, "Accesorii Telefoane", 37.99f, 0, "Shop", 1, 50, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2206/2205917/images/res_e43c1d57cc44ff2a318a7da455cc37b4_450x450_m38e.jpg");

        products.add(new Product(28, "Husa de protectie Apple pentru iPhone 6s, Silicon, Charcoal Gray", "",
                images, "Accesorii Telefoane", 182.88f, 12, "Shop", 24, 60, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3401/3400418/images/res_5b3436dc56be4b0dd14f3635e6a696ca_450x450_pp5a.jpg");

        products.add(new Product(29, "Husa de protectie Vetter Soft Pro Crystal pentru Samsung Galaxy A5 (2016), Transparent", "Conceputa din TPU (termoplastic poliuretan) de cea mai buna calitate, elastic dar in acelasi timp fiind si foarte rezistent este unul din cele mai bune materiale pentru o husa de telefon ce iti poate proteja pe tot parcursul zilei telefonul de zgarieturi, praf si lovituri",
                images, "Accesorii Telefoane", 49.99f, 0, "Shop", 12, 15, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4113/4112243/images/res_1d47f7593a7605baa99a415e05427ec9_450x450_s9f6.jpg");

        products.add(new Product(30, "Folie de protectie iWalk, sticla securizata pentru Apple iPhone 6/6s", "Suprafata anti-zgarieturi cu o duritate de 9H. Rezistent la spargere, reduce socurile si protejeaza ecranul de la spargere. Protectie impotriva mizeriei, urmelor lasate de degete, chiar si a substantelor uleioase. Transparenta ridicata. Margini rotunjite, protejeaza degetele. Adeziune automata, pentru o instalare mai rapida si mai simpla. Ultra sensibil. Protejeaza vederea",
                images, "Accesorii Telefoane", 54.99f, 18, "Shop", 0, 34, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3255/3254673/images/res_a97fb1b352695e4839fc8b6af7a177eb_450x450_bb9h.jpg");

        products.add(new Product(31, "Folie de protectie Clasic Smart Protection Samsung Galaxy S7 fullbody", "olia de protectie Clasic Smart Protection® este un produs premium creat special pentru a oferi clientilor nostri solutia unei protectii inteligente. NU este deloc o folie “clasica”, fiind compusa din 4 straturi speciale cu o grosime totala de 0.2mm. Stratul de suprafata este creat din polimeri elastomeri care „se vindeca” in timp, eliminand zgarieturile fine. In interiorul foliei se afla stratul de calitate optica si cel de dispersare a socurilor. Sub acestea se afla stratul din silicon activ ce ofera o aderenta perfecta pe orice suprafata si anduranta ridicata in timp.",
                images, "Accesorii Telefoane", 89.00f, 22, "Invisible Protection", 24, 6, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2191/2190693/images/res_5ca4cc2d128c6c73f482c951f2f13898_450x450_tksi.jpg");

        products.add(new Product(32, "Selfie Stick A+, Bluetooth, 105 cm, Verde\n", "Telescopul Selfie stick A+, este un dispozitiv ideal pentru vacante.\n" +
                "Monopodul extensibil este compatibil cu majoritatea telefoanelor, inclusiv cele mai noi dispozitive, precum Samsung Galaxy S6 sau Apple iPhone 6 / 6 Plus. Acesta permite o extensie intre 23.5-100 cm.",
                images, "Accesorii Telefoane", 49.99f, 40, "Shop", 12, 10, null));


        //Tablete si accesorii

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/1952/1951936/images/res_0d86b90da0541ee4272f32e95a958345_450x450_sum4.jpg");
        images.add("https://s5emagst.akamaized.net/products/1952/1951936/images/res_a0327d810ac3e6c0954f2b6ba03780af_450x450_um2.jpg");
        images.add("https://s5emagst.akamaized.net/products/1952/1951936/images/res_bbbc3c48b4ac34c06359111c4108c57f_450x450_3f5c.jpg");
        products.add(new Product(33, "Tableta Samsung Galaxy Tab E T560, 9.6\", Quad-Core 1.3 GHz, 1.5GB RAM, 8GB, White\n", "Maximizati timpul si productivitatea cu caracteristica interesanta de lucru cu ferestre multiple, care va permite sa lucrati in doua aplicatii in acelasi timp. Intrati in modul split-screen pentru a face mai multe lucruri in acelasi timp, cu usurinta, pe un ecran spatios de 9,6'.",
                images, "Tablete", 999.99f, 30, "Shop", 24, 21, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/1952/1951937/images/res_8111e0eaea32d401211793124cf00f5b_450x450_1e1k.jpg");
        images.add("https://s5emagst.akamaized.net/products/1952/1951937/images/res_561bae317148afa277bcde36f739ba1a_450x450_4uag.jpg");
        images.add("https://s5emagst.akamaized.net/products/1952/1951937/images/res_cdfe4f1750fd094cea814a80bb02763b_450x450_bs7n.jpg");

        products.add(new Product(34, "Tableta Samsung Galaxy Tab E T560, 9.6\"", "Capturati momentele de neuitat ale vietii cu functionalitatile avansate ale camerei dispozitivului Galaxy Tab E, inclusiv o camera spate puternica de 5 MP si controale foto si video usor de utilizat, cu un singur clic. Comutati la modul fotografiere continua tinand apasat butonul camerei.",
                images, "Tablete", 1099.99f, 27, "Shop", 24, 30, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3341/3340657/images/res_9e5a302dcd80a0615391213448dd31de_450x450_36je.jpg");
        images.add("https://s5emagst.akamaized.net/products/3341/3340657/images/res_1cba55a93757a874609cc6890782afaf_450x450_lmh4.jpg");
        images.add("https://s5emagst.akamaized.net/products/3341/3340657/images/res_f87dbe67dff49cfe3986b0114af709e8_450x450_6vhp.jpg");

        products.add(new Product(35, "Tableta Huawei MediaPad M2, 8\", Octa Core, 1.5 GHz, 2GB RAM, 16GB, 4G, IPS, Silver", "",
                images, "Tablete", 1379.99f, 10, "Shop", 24, 23, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5483/5482320/images/res_68e0af971aed7666cb84576f38ede6e8_450x450_jbfg.jpg");
        images.add("https://s5emagst.akamaized.net/products/5483/5482320/images/res_0c75a60ef611af841965140899260894_450x450_5m3l.jpg");

        products.add(new Product(36, "Apple iPad 9.7\", 32GB, Wi-Fi, Silver\n", "Studiaza, joaca-te, navigheaza, creeaza. iPad iti ofera un eran incredibil, performanta si aplicatii pentru a face ceea ce iubesti sa faci. Oriunde. Usor. Ca prin minune.",
                images, "Tablete", 1899.99f, 0, "Shop", 24, 36, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_7e81788577733c6048cb8868b2b40db3_450x450_ir8i.jpg");
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_77f4cab8e4ae5a0c6a125fc78d5827a8_450x450_2pvl.jpg");
        images.add("https://s5emagst.akamaized.net/products/4713/4712546/images/res_942385d09abb30ebda3cde888029aefc_450x450_r6tr.jpg");

        products.add(new Product(37, "Tableta ASUS ZenPad C 7.0 Z170CG-1B043A, 7\"", "Tableta ZenPad C are incorporate tehnologiile Asus Bluelight Filter si Asus TruVivid, care in combinatie cu software-ul si hardware-ul tabletei ofera o experienta vizuala de neuitat.",
                images, "Tablete", 99.99f, 40, "Shop", 0, 0, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5264/5263193/images/res_0b3177ceca8b6370f4c11166f1749b3f_450x450_5omc.jpg");
        images.add("https://s5emagst.akamaized.net/products/5264/5263193/images/res_91c4b41f6b715ab9ea4f1b7e1e8b1fd5_450x450_r3g5.jpg");

        products.add(new Product(38, "Husa de protectie A+ Smart Case pentru iPad air 2, rosu", "Husa A+ Smart Case pentru pentru pentru iPad asigura protectia integrala impotriva socurilor sau a zgarieturilor fiind foarte usoara si subtire astfel incat sa pastreze design-ul slim si elegant al tabletei. Interiorul husei este acoperit pe ambele parti cu microfibre pentru protectia ecranului sau a partii din spate a tabletei impotriva zgarieturilor.",
                images, "Accesorii Tablete", 1099.0f, 38, "iHunt", 24, 26, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3312/3311056/images/res_c29afea09e8400a0a29d97d55dd1e4f1_450x450_i67e.jpg");
        images.add("https://s5emagst.akamaized.net/products/3312/3311056/images/res_dc6061e9d79b6b147e70ccfe8cab47f3_450x450_jugm.jpg");

        products.add(new Product(39, "Husa de protectie Apple Smart Cover pentru iPad Pro 9.7\", Charcoal Grey", "",
                images, "Accesorii Tablete", 309.99f, 0, "Shop", 12, 55, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4555/4554522/images/res_0bd721d222001af7f206fd15cf2393be_450x450_3tpm.jpg");
        images.add("https://s5emagst.akamaized.net/products/4555/4554522/images/res_cac66904e2bffafba2d5e0f0cb348ce0_450x450_neal.jpg");
        images.add("https://s5emagst.akamaized.net/products/4555/4554522/images/res_48896cb651c9100069645433d498c1c7_450x450_mikn.jpg");

        products.add(new Product(40, "Husa Slim Book Cover Samsung Tab A T580 (2016)", "• Husa book cover, piele ecologica, calitate premium;\n" +
                "Husa este confectionata si conceputa pentru a se potrivi perfect cu tableta;\n" +
                "Inchidere MAGNETICA, nu afecteaza in niciun fel functionarea tabletei;\n" +
                "Senzor magnetic inserat in coperta pentru activarea ecranului si functia de stand-by;\n" +
                "Design slim;\n" +
                "Acces la functiile de baza, decupaje camera, butoane laterale, casti etc;\n" +
                "Protectie pentru ecran prin coperta intarita.",
                images, "Accesorii Tablete", 65.45f, 9, "QuickMobile", 1, 26, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2888/2887823/images/res_f537e932f740e1b6dab6ea21cdcd9f10_450x450_2vom.jpg");

        products.add(new Product(41, "Folie Sticla Tempered Glass Premium pentru tableta Asus ZenPad Z380, 8 inch", "Folia de protectie din sticla securizata “Tempered Glass”, este construita din sticla de inalta calitate, tratata termic. Ofera protectie ridicata anti-soc, testata si in acelasi timp pastreaza calitatea maxima a culorilor, luminozitatii si contrastului display-ului.\n",
                images, "Accesorii Tablete", 59.50f, 30, "QuickMobile", 1, 47, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_9e20681df804c5dc2119b8c1d3b0bcc9_450x450_rarf.jpg");
        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_73bff447fb28cfe34ac85ccad9f6cc9f_450x450_pumi.jpg");
        images.add("https://s5emagst.akamaized.net/products/2867/2866463/images/res_5c632684b966e3b40578eb9470a03b45_450x450_a7kn.jpg");

        products.add(new Product(42, "Tastatura/docking pentru Chuwi Hi12\n", "Produse compatibile\n Tableta PC Chuwi Hi12, Intel Atom Z8300, 12 inch,2160x1440, IPS, 4GB RAM, 64GB, Wi-Fi, Windows 10+Android 5.1, 11000 mAh",
                images, "Accesorii Tablete", 199.99f, 0, "PNI", 24, 13, null));








        //TV, Audio, Foto si Gaming
        //Televizoare si accesorii


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3375/3374085/images/res_ba51671729dba1937e16c8b3be68e976_450x450_uk30.jpg");
        images.add("https://s5emagst.akamaized.net/products/3375/3374085/images/res_fd89442d2ce10a588656814a5c344215_450x450_15bt.jpg");
        images.add("https://s5emagst.akamaized.net/products/3375/3374085/images/res_0573ee87c53322f552d0a8946b0251b5_450x450_qtqk.jpg");
        products.add(new Product(43, "Televizor LED LG, 80 cm, 32LH500D, HD", "Cea mai noua si avansata tehnologie LG, Triple XD Engine, ofera cel mai inalt grad de excelenta in privinta culorii, contrastului si claritatii, pentru cea mai buna calitate a imaginii si performanta.",
                images, "Televizoare", 1199.99f, 10, "Shop", 24, 36, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3259/3258575/images/res_bb3b8dcde672513a4d9cf76095a53a6a_450x450_odsn.jpg");
        images.add("https://s5emagst.akamaized.net/products/3259/3258575/images/res_6b7377ea3f4cf0866db6864f6c591751_450x450_jp87.jpg");
        images.add("https://s5emagst.akamaized.net/products/3259/3258575/images/res_a21acc75c690116ac524992f5df36cab_450x450_99c2.jpg");



        products.add(new Product(44, "Televizor LED Smart Samsung, 101 cm, 40KU6072, 4K Ultra HD", "Samsung a dezvoltat tehnologia PurColour de optimizare a culorilor, pentru ca televizorul dumneavoastra sa redea o gama mai larga de culori si nuante, mai apropiate celor din natura. Datorita celor 8 milioane de pixeli ai unui ecran UHD (de patru ori mai multi decat un ecran Full HD), este nevoie de mai multe puncte de ajustare a culorii, pentru a crea imagini detaliate. Astfel, comparativ cu televizoarele UHD conventionale ce au aproximativ 27 de puncte de ajustare a culorii, PurColour integreaza o tehnologie cu de peste 7 ori mai multe puncte de analiza, obtinand culori si nuante mult mai bogate.",
                images, "Televizoare", 2999.99f, 20, "Shop", 24, 33, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2429/2428139/images/res_4f0490519c786b3bfe9ce072df5044c7_450x450_2m64.jpg");
        images.add("https://s5emagst.akamaized.net/products/2429/2428139/images/res_0b30dc8d38f853b5125223d11216b819_450x450_qf2v.jpg");
        images.add("https://s5emagst.akamaized.net/products/2429/2428139/images/res_0599336b3134843c7350f3e466dd9a7d_450x450_aet6.jpg");

        products.add(new Product(45, "Televizor LED Smart Samsung, 100 cm, 40J5200, Full HD", "Bucurati-va de noul standard al divertismentul la domiciliu. Datorita rezolutiei duble fata de cea a televizoarelor HD standard, televizorul dvs. Full HD Samsung face ca experienta de vizionare sa fie una de exceptie, oferind o imagine mai clara si mult mai multe detalii. Dupa ce experimentati culorile vii si bogate ale imaginilor Full HD, filmele si programele dvs. TV preferate nu vor mai fi niciodata la fel. Descoperiti realitatea in mai multe detalii pentru o experienta de imbunatatita de vizionare.",
                images, "Televizoare", 1899.99f, 0, "producer", 24, 0, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4621/4620033/images/res_d088da977e79dd68aa7895165e44ff38_450x450_h8f8.jpg");

        products.add(new Product(46, "Televizor LED Star-Light, 80 cm, 32DM2200, HD Ready", "Fie ca vreti sa urmariti meciul echipei preferate, sa priviti peisajele minunate din emisiunile educative sau sa urmariti serialul preferat, descoperiti magia culorilor vii cu Televizorul Star-Light 32DM2200. Asadar, daca sunteti in cautarea unui televizor LED ultra-slim cu ecran HD, acest televizor poate reprezenta alegerea perfecta pentru dumneavoastra.",
                images, "Televizoare", 842.91f, 0, "Shop", 24, 10, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4528/4527402/images/res_34c33a15488a1169cf8a27f0c4de932f_450x450_tgkj.jpg");

        products.add(new Product(47, "Televizor LED Star-Light, 127 cm, 50DM5500, Full HD", "Fie ca vreti sa urmariti meciul echipei preferate, sa priviti peisajele minunate din emisiunile educative sau sa urmariti serialul preferat, descoperiti magia culorilor vii cu Televizorul Star-Light 50DM5500. Asadar, daca sunteti in cautarea unui televizor LED ultra-slim cu ecran HD, acest televizor poate reprezenta alegerea perfecta pentru dumneavoastra.",
                images, "Televizoare", 1779.99f, 21, "Shop", 24, 40, null));


        //Accesorii TV
        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2730/2729906/images/res_2a5dee8058ccc534c34c2ed0c4e104b0_450x450_ph0s.jpg");

        products.add(new Product(48, "Ochelari anaglifici 3D red-cyan, model aviator", "Ochelarii 3D permit vizualizarea imaginilor 3D (anaglife), a filmelor 3D, a paginilor web 3D, etc.",
                images, "Accesorii TV", 24.05f, 23, "Albacom Biz", 12, 23, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4408/4407289/images/res_240e72afd50837426814a177ec9b1965_450x450_8229.jpg");

        products.add(new Product(49, "Telecomanda AA59-00594A Samsung LCD", "Telecomanda AA59-00594A Samsung pentru LCD, Smart, LED. Telecomanda AA59-00594A este folosita pentru urmatoarele modele de televizoare: UA55F8000AJ, UA55F6400AJXXZ, UA55F6420AJ, etc.",
                images, "Accesorii TV", 33.00f, 0, "Albacon Biz", 12, 50, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4408/4407302/images/res_91eb1db96d21bb4bfa8acbb869aff2eb_450x450_h6s2.jpg");


        products.add(new Product(50, "Telecomanda universala LCD RML815", "Telecomanda LCD universala RM-L815. Compatibila cu: JVC, KONKA, PRIMA, PHILIPS, KYWORTH, CHANGHONG, PANASONIC, OTHER, HISENSE, SHARP, DAEWOO, THOMSON, TOSHIBA, HAIER, SAMSUNG, HITACHI, LG, SONY, SANYO.",
                images, "Accesorii TV", 25.0f, 0, "shopU", 12, 3, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/1726/1725261/images/res_1a7829f65d3534513ea61c332ac8dea3_450x450_787c.jpg");

        products.add(new Product(51, "Cablu A+ Premium High-Speed HDMI 1.4V, plug-plug, Ethernet, 3D, gold-plated, 2 m", "Cablul A+ High-Speed HDMI poate fi utilizat pentru diferite tipuri de dispozitive cu port HDMI: calculatoare, laptop-uri, televizoare, monitoare, etc.",
                images, "Accesorii TV", 44.99f, 22, "Shop", 24, 60, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2274/2273870/images/res_8bd8ed06a8763ca260d68c24d2eec775_450x450_7sm8.jpg");
        images.add("https://s5emagst.akamaized.net/products/2274/2273870/images/res_93aa516a083dff3be9cc4a520a72cf1f_450x450_v7kp.jpg");
        images.add("https://s5emagst.akamaized.net/products/2274/2273870/images/res_e40528aacf058d91345c30698632a822_450x450_agp0.jpg");

        products.add(new Product(52, "Suport TV de perete Hama Full Motion 118667, 2 Brate, Reglabil, 23\"-56\", 35 kg, Negru", "Sistemul Easy-Fix asigura orientarea simpla si continua a aparatului TV",
                images, "Accesorii TV", 248.99f, 0, "Flanco", 24, 25, null));


        //Audio


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/419/418949/images/res_37f6cb741b322b485171c1e46a64f274_450x450_i06e.jpg");

        products.add(new Product(53, "Sistem Boxe Pasive AKAI SS016A-655MK, 70W RMS, Negru", "",
                images, "Audio", 549.99f, 10, "Shop", 24, 30, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/856/855529/images/res_28235337f279ebcdfe521eb01c1544d3_450x450_gsh.jpg");
        images.add("https://s5emagst.akamaized.net/products/856/855529/images/res_cec40f18cfa1e641b3728b2675907cf8_450x450_1u91.jpg");

        products.add(new Product(54, "Set 2 boxe de raft MAGNAT Monitor Supreme 102 culoare mocca", "Pereche boxe pasive de interior\n",
                images, "Audio", 399.00f, 0, "Alarm Service", 12, 50, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/649/648968/images/res_09e64d001058fa93230dddcf8ab516e8_450x450_1dlu.jpg");
        images.add("https://s5emagst.akamaized.net/products/649/648968/images/res_2a80793f0f2ae4963a32d40453c499f5_450x450_6vf7.jpg");

        products.add(new Product(55, "Sistem Audio 5.1 Akai AS030RA-780B + Sistem Boxe SS006A-305, 200W RMS, Negru", "Acesta este un receiver audio AKAI ce nu trebuie sa lipseasca celor care iubesc cu adevarat muzica.\n" +
                "Este un sistem 5.1 cu o putere mare de iesire, putandu-se da o adevarata petrecere ori de cate ori simtiti nevoia.\n" +
                "Vine cu un design metalic, ce se va integra de minune in orice incapere.",
                images, "Audio", 1699.999f, 0, "Shop", 24, 60, null));



        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/918/917406/images/res_bccc14d3f7b79f7a9de4143f4d57d4f5_450x450_6muu.png");
        images.add("https://s5emagst.akamaized.net/products/918/917406/images/res_e7189df163849f10e813fca5c43fb609_450x450_ah7i.jpg");

        products.add(new Product(56, "Amplificator integrat Marantz PM7005, 2.0, 80W, Negru", "PM7005 este un amplificator integrat de tip current feedback complet discret cu functionalitate USB-DAC. Este perfect pentru audiofilul care cauta atat calitate muzicala excelenta, cat si operatiune cu maxima flexibilitate, ambele oferite de acest amplificator datorita modului DAC extrem de versatil.",
                images, "Audio", 3999.00f, 25, "Alarm Service", 0, 0, null));




        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/869/868321/images/res_de302bbe78592c162ed9053d233771f8_450x450_trh1.jpg");
        images.add("https://s5emagst.akamaized.net/products/869/868321/images/res_a6e44f265e574b46412ff129ea64967f_450x450_is3u.jpg");

        products.add(new Product(57, "Procesor Crossover BEHRINGER CX3400", "CX3400 este un crossover activ care poate opera in 2 sau 3 cai stereo sau 4 cai mono. Fiecare banda are filtre Linkwitz-Riley 24dB precum si limitari individuale IGC in timp ce delay-ul integrat permite o aliniere temporala flexibila. Limitor individual si switch pentru inversare de faza / iesire. Filtru subsonic de 25Hz interschimbabil pe fiecare intrare pentru protejarea difuzorului de joase.\n",
                images, "Audio", 481.95f, 0, "Alarm Service", 24, 30, null));


        //Aparate foto si accesorii
        //Aparate foto

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/53/52100/images/0_450x450_mov8.jpg");
        images.add("https://s5emagst.akamaized.net/products/53/52100/images/3_450x450_nrji.jpg");
        images.add("https://s5emagst.akamaized.net/products/53/52100/images/4_450x450_c0bs.jpg");
        images.add("https://s5emagst.akamaized.net/products/53/52100/images/5_450x450_u0vt.jpg");

        products.add(new Product(58, "Aparat foto digital Sony Cyber-Shot DSC-HX300, 20MP, Black", "HX300 Camera digitala compacta\n" +
                "Fotografiati ca un profesionist cu zoomul puternic",
                images, "Aparate foto", 1589.99f, 24, "Shop", 24, 80, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3071/3070167/images/res_d2202427ec18731a475d68de80c59bf2_450x450_uubu.jpg");
        images.add("https://s5emagst.akamaized.net/products/3071/3070167/images/res_64e6a1459cce40058756e63e80d78146_450x450_bblb.jpg");

        products.add(new Product(59, "Aparat foto digital AquaPix W1400 Active Waterproof, 20 MPx, Dustproof, Shockproof, Albastru (Dual Display, Ideal pentru Selfie-uri Sub Apa)", "",
                images, "Aparate foto", 349.00f, 29, "Atu IT", 24, 40, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2966/2965647/images/res_59bbf8c90ccc7ba780c412035c02697f_450x450_jqb4.jpg");
        images.add("https://s5emagst.akamaized.net/products/2966/2965647/images/res_fff56607c84b32ad04bf23ad774d463d_450x450_dc9b.jpg");

        products.add(new Product(60, "Aparat foto DSLR Nikon D3300, 24.2MP, Red + Obiectiv AF-P 18-55mm VR", "Viata este plina de momente surprinzatoare si vesele, momente pe care merita sa ni le amintim. D3300 face mai usoara si distractiva pastrarea acestor momente prin fotografii uimitoare de 24,2 megapixeli si  video Full HD 1080p cu detalii clare, culori vibrante si fundaluri usor neclare.",
                images, "Aparate foto", 1999.99f, 5, "Atu IT", 24, 0, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5841/5840630/images/res_9ba04df841db99e96a4efec8e91efc7e_450x450_1kn4.jpg");


        products.add(new Product(61, "Aparat Foto Olympus E-PL7 Pancake Zoom Kit slv/slv (E-PL7 silver + EZ-M1442EZ silver)", "Indiferent de cat de usoara este camera, atunci cand este tinuta cu mana suficient de mult timp pentru a capta o secventa, neclaritatea poate pune probleme. Acesta este motivul pentru care Olympus a utilizat tehnologia de stabilizare pe 3 axe. Pe scurt, aceasta contracareaza miscarile de rotatie ale mainii mult mai eficient decat sistemele obisnuite existente pe camere comparabile. ",
                images, "Aparate foto", 2529.00f, 0, "Alarm Service", 24, 10, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2752/2751147/images/res_e14b1b9bfe323420a6209f59a24e55c3_450x450_va39.jpg");


        products.add(new Product(62, "Camera foto instant Fujifilm Instax mini 70 alb", "description",
                images, "Aparate foto", 649.00f, 0, "Shop", 0, 2, null));


        //Accesorii foto

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2033/2032814/images/res_8a377e17f689ddc3616e4d27f445ecaa_450x450_15fg.jpg");
        images.add("https://s5emagst.akamaized.net/products/2033/2032814/images/res_9bcb95666b96bf00f807c7b696a178bb_450x450_2nt1.jpg");
        images.add("https://s5emagst.akamaized.net/products/2033/2032814/images/res_d4bbde14be3a4a0b12a3ff57f00f3108_450x450_vgbf.jpg");

        products.add(new Product(63, "Trepied foto Fancier WT3570", "repiedul Fancier WT-3570 este fabricat din din aluminiu si este prevazut cu un cap panoramic (3D). Fancier WT-3570 este usor de transportat si foarte simplu de utilizat si ofera o buna stabilitate aparatelor foto/video compacte sau (D)SLR-urilor entry level cu obiectiv de kit, cum sunt: Nikon D3100, D3200, D5100, Sau Canon EOS 600d, EOS 650D. - inaltime maxima: 164cm - inaltime minima: 62.5cm - inaltimea trepiedului pliat: 68.2cm - diametrul maxim al picioarelor: 28.8mm - greutate: 1.64Kg - suporta sarcina de pana la 4kg.",
                images, "Aparate foto", 149.00f, 0, "producer", 12, 50, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/503/502519/images/res_89560a114b0cd3e1437609dc1ba0208d_450x450_qljn.jpg");


        products.add(new Product(64, "Card de memorie Hama MicroSDHC, 16GB, Class 10 + Adaptor", "Hama microSDHC 16GB, Clasa 10 este un card de memorie cu adaptor SD, pentru tablete, telefoane si camere de actine sau camere auto.",
                images, "Accesorii foto", 49.99f, 20, "Shop", 60, 3, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/4244/4243267/images/res_e772e03e578b0943192663bc0c801c8a_450x450_r3b2.jpg");
        images.add("https://s5emagst.akamaized.net/products/4244/4243267/images/res_cd2a8fc1c5b220ada0cce81ac7ef3d12_450x450_oifj.jpg");
        images.add("https://s5emagst.akamaized.net/products/4244/4243267/images/res_1da3735db9cb0e5221d1b3c3ae2e74c7_450x450_p5tm.jpg");

        products.add(new Product(65, "Geanta camera foto A+ small, Black", "Geanta camera foto A+ este compatibila cu cele mai multe aparate foto SLR.",
                images, "Accesorii foto", 89.99f, 0, "Shop", 6, 0, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/374/373682/images/img108605_20012009102529_450x450_kc28.jpg");

        products.add(new Product(66, "Rama foto digitala + imprimanta foto Abit P71-A2\n", "Rama foto digitala + imprimanta foto | LCD 7 480x234 | format foto 4x6 | 300 dpi | tehnologie dye sublimation | memorie interna 128 MB | card-uri suportate SD, xD, MMC, MS | Calendar Function | Clock Function | port USB",
                images, "Accesorii foto", 1521.00f, 0, "Shop", 24, 0, null));






        //Gaming

        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/5354/5353493/images/res_ce23f3f37860fec2dcb68cafeeba7624_450x450_gt4p.jpg");
        images.add("https://s5emagst.akamaized.net/products/5354/5353493/images/res_930b327b4e0797a925384b1665c40715_450x450_rkme.png");
        images.add("https://s5emagst.akamaized.net/products/5354/5353493/images/res_6d9363d64ef9dfdc6d68dce02e919852_450x450_eqhk.png");

        products.add(new Product(67, "Consola Microsoft Xbox One Slim 500 GB, White", "Noul controler Xbox dispune de un design elegand si simplificat, manere texturate si tehnologie Bluetooth pentru jocuri pe dispozitive Windows 10. Bucurati-va de un buton personalizat si de o raza wireless extinsa si conectati cu orice set compatibil de casti cu mufa jack 3.5mm.",
                images, "Console", 999.99f, 0, "producer", 24, 50, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_cbc9bb4dc595cd0a3d75d5d28176392f_450x450_cc9c.jpg");
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_48292b59a47841affa727478857043eb_450x450_5h3j.jpg");
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_7421dd6e255a640644f9ae2aa5db8c4a_450x450_af2o.png");
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_6e6206309513ed020961e1dc11a47cf6_450x450_q8v8.jpg");
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_1393367c4ac942b7cef3232390d4663c_450x450_gpgl.jpg");
        images.add("https://s5emagst.akamaized.net/products/901/900670/images/res_b3f57dd2821b12349848366b3efcb0aa_450x450_7afm.jpg");

        products.add(new Product(68, "Consola Microsoft Xbox 360, 500 GB + Kinect + Joc Kinect Adventures + Joc Kinect Sports + Joc Forza Horizon", "",
                images, "Console", 1684.00f, 10, "Ventum", 0, 20, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/770/769653/images/img256067_23052013150745_0_450x450_gaqh.jpg");
        images.add("https://s5emagst.akamaized.net/products/770/769653/images/img256067_23052013150709_1_450x450_3218.jpg");
        images.add("https://s5emagst.akamaized.net/products/770/769653/images/res_441a529d591db656a090063d425f71e2_450x450_7naj.jpg");

        products.add(new Product(69, "Consola Sony Playstation 3 Super Slim, 12GB, Negru", "Multumita celui mai noi versiuni de Firmware (ce poate fi descarcata de la aceasta adresa), poti juca jocurile 3D cu efecte ce te vor introduce si mai mult in lumea gamingului. Ai nevoie de un televizor 3D (cu ochelari compatibili) si un cablu HDMI de inalta viteza.",
                images, "Console", 899.00f, 5, "Ventum", 24, 20, null));

        //Accesorii
        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3987/3986157/images/res_1e4a201013706ce2f4cfab11c1db48fa_450x450_bgut.jpg");
        images.add("https://s5emagst.akamaized.net/products/3987/3986157/images/res_11460cb99de1083d20df92b7ca708876_450x450_m0jk.jpg");
        images.add("https://s5emagst.akamaized.net/products/3987/3986157/images/res_0e5830c13ee142f65d35d5c3e4169007_450x450_40t8.jpg");

        products.add(new Product(70, "Casti audio Gaming Trust GXT 353 Vibration pentru PC, PlayStation 4", "Casti iluminate gaming cu vibratie bas",
                images, "Accesorii Gaming", 189.99f, 0, "Shop", 24, 40, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/10/9778/images/res_9734bc14e460c59fe6ad0e73e94cd05a_450x450_q7jc.jpg");
        images.add("https://s5emagst.akamaized.net/products/10/9778/images/res_85274bde3373f7d389f9625e0172eb7e_450x450_4c8.jpg");


        products.add(new Product(71, "Joystick Logitech Extreme 3D Pro pentru PC", "Cercetarile au aratat ca zborul avansat se reduce la instincte si timp de reactie. Pentru a ajuta pilotii, baietii nostri din laborator au proiectat Extreme 3D Pro, rezultand un control natural cu o singura mana, cu o amprenta la sol a dispozitivului mai mica.",
                images, "Accesorii Gaming", 247.99f, 5, "Shop", 24, 20, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/2717/2716257/images/res_8406ae33707a6c960f589453eb77b321_450x450_um6e.jpg");

        products.add(new Product(72, "Gamepad Thrustmaster GPX BLACK (PC, XBOX360) - 4460091 USB, PC, XBox 360, Negru", "Ergonomie rafinata pentru confort si manevrabilitate optime, suprafata inferioara anti-alunecare .",
                images, "Accesorii Gaming", 120.00f, 0, "IT2GO", 24, 0, null));


        //Jocuri
        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3709/3708844/images/res_001a3efb9a57ff71f6b46ea2b6895369_450x450_sf39.jpg");


        products.add(new Product(73, "Joc FIFA 17 pentru PC", "Realizat cu ajutorul motorului grafic Frostbite, FIFA 17 transforma intr-un mod complet inovativ felul in care joci, concurezi si interactionezi cu jocul intr-o experienta autentica de fotbal, gratie unui nou motor grafic sofisticat, care iti permite sa iti inovezi tehnicile si tacticile de joc.",
                images, "Jocuri", 189.99f, 0, "Shop", 0, 30, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3556/3555389/images/res_6e87bb9d64d6d779cfa1e96725049ea9_450x450_6kd0.jpg");
        images.add("https://s5emagst.akamaized.net/products/3556/3555389/images/res_17652424ccbf0ed6a7fc40cb63ba1955_450x450_ea56.jpg");
        images.add("https://s5emagst.akamaized.net/products/3556/3555389/images/res_d1a886d2697aad3826e9d681a72cf13d_450x450_j4tg.jpg");

        products.add(new Product(74, "Joc Battlefield 1 PC FRONTLINE", "Battlefield 1 FRONTLINE pentru PC",
                images, "Jocuri", 199.99f, 5, "Shop", 0, 40, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3709/3708855/images/res_d928512cc37f4521a632a67147e4c121_450x450_bo6s.jpg");


        products.add(new Product(75, "Joc TITANFALL 2 pentru PlayStation 4", "",
                images, "Jocuri", 214.99f, 10, "Shop", 0, 20, null));


        images = new ArrayList<>();
        images.add("https://s5emagst.akamaized.net/products/3614/3613418/images/res_efc6dede1156209461d8ce6d49d6f702_450x450_agt8.jpg");
        images.add("https://s5emagst.akamaized.net/products/3614/3613418/images/res_90f69ecb595eb1eaf4454b3821d63931_450x450_8789.jpg");
        images.add("https://s5emagst.akamaized.net/products/3614/3613418/images/res_e2f8d68916c112597e9a6b6c639ee9f5_450x450_4cuf.jpg");

        products.add(new Product(76, "Joc DISHONORED 2 pentru PC", "DISHONORED 2 pentru PC",
                images, "Jocuri", 184.99f, 15, "Shop", 0, 0, null));

*/
        /*images = new ArrayList<>();
        images.add("");
        images.add("");
        images.add("");

        products.add(new Product(id, "name", "description",
                images, "category", 199.99f, 0, "producer", 24, 13));*/









    }
}
