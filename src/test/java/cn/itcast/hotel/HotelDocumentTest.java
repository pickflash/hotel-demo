package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.impl.HotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static cn.itcast.hotel.constants.HotelConstants.MAPPING_TEMPLATE;

@SpringBootTest
public class HotelDocumentTest {
    private RestHighLevelClient client;

    @Autowired
    private HotelService hotelService;
    //添加文档
    @Test
    void testAddDocument() throws IOException {
        //根据id查询酒店数据
        Hotel hotel = hotelService.getById(61083L);
        //转换为文档类型
        HotelDoc hotelDoc=new HotelDoc(hotel);

        //1,准备Request对象
        IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
        //2，准备json文件
        request.source(JSON.toJSONString(hotelDoc),XContentType.JSON);
        //3，发送请求
        client.index(request,RequestOptions.DEFAULT);
    }
    //获取文档
    @Test
    void testGetDocumentById() throws IOException {
        //1,创建request对象
        GetRequest request = new GetRequest("hotel","61083");
        //2，发送请求，得到结果
        GetResponse response=client.get(request,RequestOptions.DEFAULT);
        //3，解析结果
        String json=response.getSourceAsString();
        //反序列化
        HotelDoc hotelDoc=JSON.parseObject(json,HotelDoc.class);

        System.out.println(hotelDoc);
    }
    //修改文档
    @Test
    void testUpdateDocument() throws IOException {
        //1,创建request对象
       UpdateRequest request = new UpdateRequest("hotel","61083");
        //2，准备请求参数
        request.doc(
                "price","952",
                "starName","四钻"
        );
        //3，发送请求
        client.update(request,RequestOptions.DEFAULT);
    }
    //删除文档
    @Test
    void testDeleteDocument() throws IOException {
        //1,创建request对象
        DeleteRequest request = new DeleteRequest("hotel","61083");
        //2，发送请求
        client.delete(request,RequestOptions.DEFAULT);
    }

    //案例
    @Test
    void testBulkRequest() throws IOException {
        //1,创建Request
        BulkRequest request=new BulkRequest();

        //批量查询酒店数据
        List<Hotel> hotels=hotelService.list();
        //转换位文档类型
        for(Hotel hotel:hotels){
            HotelDoc hotelDoc=new HotelDoc(hotel);
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source("json",XContentType.JSON));
        }
        //3,发送请求
        client.bulk(request,RequestOptions.DEFAULT);
    }


    /*客户端初始化*/
    @BeforeEach
    void setUp(){
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.150.101:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }

    @Test
    void createHotelIndex() throws IOException {
        //1,创建request对象
        CreateIndexRequest request=new CreateIndexRequest("hotel");
        //2，准备请求的参数：DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        //3，发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }
    @Test
    void deleteHotelIndex() throws IOException {
        //1,创建request对象
        DeleteIndexRequest request=new DeleteIndexRequest("hotel");
        //2，发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }
    @Test
    void existHotelIndex() throws IOException {
        //1,创建request对象
        GetIndexRequest request=new GetIndexRequest("hotel");
        //2，发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        //3.输出
        System.out.println(exists?"索引库已经存在！":"索引库不存在！");
    }
}


