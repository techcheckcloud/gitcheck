sap.ui.define(
	["sap/ui/core/mvc/Controller",
		"jquery.sap.global",
		"sd/util/service",
		"sap/m/MessageBox"],
	function(Controller, jQuery, service, MessageBox) {
		return Controller.extend("sd.controller.Main", {
			onInit: function() {
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({
					"postPayload": {
						"companyName": "",
						"firstName": "",
						"lastName": "",
						"website": "",
						"email": "",
						"status": "S",
						"gstNo": ""
					},
					"vendor": { }
				});
				sap.ui.getCore().setModel(oModel);
			},
			onSave: function(){
			var oModel = sap.ui.getCore().getModel();
			var payload = oModel.getProperty("/postPayload");
			service.callService("/vendor","POST",payload).then(function(){
				MessageBox.confirm("Saved Success");
			}).catch(function(){
				MessageBox.error("post failed");
			});	
			},
			onLoadData: function() {
				//alert("todo: will call our microservice to load vendors");
				var that = this;
				service.callService("/newVendor", "GET",{ }).then(function(data) {
					//console.log(data);
					var oTable = that.getView().byId("idTable");
					var oModel = new sap.ui.model.json.JSONModel();
					var oModel = sap.ui.getCore().getModel();
					oModel.setProperty("/vendor", data._embedded.vendor);
					//oTable.setModel(oModel);
					sap.ui.getCore().setModel(oModel);
					oTable.bindRows("/vendor");
				}).catch(function(oError) {

				});

			}
		});
	});