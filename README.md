# CMS

### Web port
1. manager: 20000
2. manager-course: 20100
3. client: 21000
4. manager-fs: 20200

## MQ
1. PageRelease, manager -> client
* exchange: exchange_routing_cms_page_release
* routing key: site id
* queue name style: queue_cms_page_release_xx
