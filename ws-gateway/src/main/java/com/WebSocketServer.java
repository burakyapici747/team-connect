package com;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

public class WebSocketServer {
    private final int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public WebSocketServer(int port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    public void start(){
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup();

        try {
            //Server bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            //Boss ve worker thread gruplari server'a eklenir
            bootstrap.group(bossGroup, workerGroup)
                //Non-blocking I/O Channel Type Event loop architecture
                .channel(NioServerSocketChannel.class)
                //boss Group tarafindan yeni bir baglanti kabul edilip workerGroup'a atandiginda, o baglantiyi nasil yapilandiracagimiz belirler.
                //Her yeni baglanti icin initChannel metodu bir kez calistirilir.
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new HttpServerCodec());
                        //Gelen HTTP istekleri FullHttpRequest objesine donusturmek icin
                        //max birlestirilebilir boyut 65536
                        pipeline.addLast(new HttpObjectAggregator(65536));

                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        pipeline.addLast(new WebsocketFrameHandler());
                    }
                })

                // ServerSocketChannel accept() çağrısı yapmadığında, TCP bağlantısı kurulmuş (handshake tamamlanmış)
                // ancak henüz uygulama tarafından kabul edilmemiş istemci bağlantılarının,
                // işletim sistemi seviyesindeki kuyrukta en fazla kaç tanesinin bekletilebileceğini belirler.
                .option(ChannelOption.SO_BACKLOG, 128)
                // TCP'nin keep alive ozelligini aktif eder ve baglantinin kopup kopmadigini her channel icin kontrol eder.
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            //
            ChannelFuture future  = bootstrap.bind(port).sync();
            System.out.println("WebSocket Server started on port " + port);

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(" Finallyyyyyyyy");
            shutdown();
        }
    }

    public void shutdown(){
        if(this.bossGroup != null){
            this.bossGroup.shutdownGracefully();
        }
        if(this.workerGroup != null){
            this.workerGroup.shutdownGracefully();
        }
    }
}
