module.exports = {
    bookMarkVipAdress: "bookmark-service",
    bookVipAdress: "book-service",
    accountVipAddress: "account-service",
    getAccountRootUrl: function (client) {
        return this.getRootUrlByVipAdress(client, this.accountVipAddress);
    },
    getBookmarkRootUrl: function (client) {
        return this.getRootUrlByVipAdress(client, this.bookMarkVipAdress);
    },
    getBookRootUrl: function(client){
        return this.getRootUrlByVipAdress(client, this.bookVipAdress);
    },
    getRootUrlByVipAdress: function (client, vipAddress) {
        var oneInstance = this.getOneInstanceFromAll(client, client.getInstancesByVipAddress(vipAddress));
        return this.getServerPath(oneInstance);
    },

    /**
 * 获取某个可用服务,随机取
 * @param client CoordinatorClient
 * @param instances 所有实例
 * @returns {*}
 */

    getOneInstanceFromAll: function (client, instances) {
        if (instances != null) {
            var upInstances = [];
            for (var i = 0; i < instances.length; i++) {
                if (instances[i].status.toUpperCase() === "UP") {
                    upInstances.push(instances[i]);
                }
            }
            if (upInstances.length > 0) {
                let instanceIndex = upInstances.length===1?0:Date.now()%(upInstances.length-1);
                return upInstances[instanceIndex];
            } else {
                return "";
            }
        } else {
            return "";
        }
    },

    /** Thanks to  coordinator-node-client */
    /**
         * 根据实例获取一个完整的ip方式的服务地址。
         * @param instance  app的实例。
         * @returns {string}  url地址,包括协议，ip和端口。例如:http://192.168.1.100:8080。
         */
    getServerPath: function (instance) {
        var url = "", http = "http://", https = "https://";
        if (instance) {
            if (instance.port && instance.port["@enabled"] == "true") {
                url = http + instance.ipAddr + ":" + instance.port["$"];
            } else if (instance.securePort && instance.securePort["@enabled"] == "true") {
                url = https + instance.ipAddr + ":" + instance.securePort["$"];
            }
        }
        return url;
    }


}