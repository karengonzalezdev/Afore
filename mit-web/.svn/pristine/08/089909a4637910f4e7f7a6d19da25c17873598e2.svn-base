/************************************************************************************************************************************
 *	fb.wall				Facebook Wall jQuery Plguin
 *
 *	@author:			Daniel Benkenstein / neosmart GmbH
 *	@version:			1.2.7
 *	@Last Update:		06.06.2011
 *	@licence:			MIT (http://www.opensource.org/licenses/mit-license.php)
 *						GPL	(http://www.gnu.org/licenses/gpl.html)
 *	@documentation:		http://www.neosmart.de/social-media/facebook-wall
 *	@feedback:			http://www.neosmart.de/blog/jquery-plugin-facebook-wall
 *	
 ************************************************************************************************************************************/

(function($) {
	
	$.fn.fbWall = function(options) {
		
		var opts = $.extend({}, $.fn.fbWall.defaults, options);
		var meta = this;
		
		return meta.each(function() {
			$this = $(this);
			var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
			var output = '';
			var avatarBaseURL;
			var baseData;
			var graphURL = "https://graph.facebook.com/";
			
			/******************************************************************************************************
			 * Load base data
			 ******************************************************************************************************/
			 
			meta.addClass('fb-wall').addClass('loading').html('');
			$.ajax({
				url: graphURL+o.id+'?access_token='+o.accessToken,
				dataType: "jsonp",
				success: function(data, textStatus, XMLHttpRequest){
					initBase(data);
				}
			});
			
			/******************************************************************************************************
			 * Load feed data
			 ******************************************************************************************************/
			 
			var initBase = function(data){
				baseData = data;
				
				if(data==false){
					meta.removeClass('loading').html('The alias you requested do not exist: '+o.id);
					return false;
				};
				
				if(data.error){
					meta.removeClass('loading').html(data.error.message);
					return false;
				};
				
				var type = (o.showGuestEntries=='true'||o.showGuestEntries==true) ? 'feed' : 'posts';
				$.ajax({
					url: graphURL+o.id+"/"+type+"?limit="+o.max+'&access_token='+o.accessToken,
					dataType: "jsonp",
					success:function (data, textStatus, XMLHttpRequest) {
						meta.removeClass('loading');
						initWall(data);
					}
				});
			}
	
			/******************************************************************************************************
			 * Parse feed data / wall
			 ******************************************************************************************************/
			 
			var initWall = function(data){
				
				data = data.data;
				
				var max = data.length;
				var thisAvatar, isBase, hasBaseLink, thisDesc;
				
				var andforward = new Array();
				
				for(var k=0;k<max;k++){
					var t = new Object();
					if(exists(data[k].message)) t.message = modText(data[k].message);
					if(exists(data[k].id)) {
						t.id = data[k].id;
						t.id = t.id.substr(t.id.indexOf("_")+1);						
						t.url = "http://facebook.com/"+o.id+"/posts/"+t.id;
					}
					if (exists(data[k].created_time)) t.created_time = Date.parse(data[k].created_time);
					t.source = "fb";
					andforward[k] = t;
				}
				
			  	if (typeof o.callback === "function") {
			        o.callback(andforward);
			    }
			}
			
			/******************************************************************************************************
			 * Get Avatar URLs
			 ******************************************************************************************************/
			
			function getAvatarURL(id){
				var avatarURL;
				if(id==baseData.id){ avatarURL = (o.useAvatarAlternative) ? o.avatarAlternative : graphURL+id+'/picture?type=square'; }
				else{ avatarURL = (o.useAvatarExternal) ? o.avatarExternal : graphURL+id+'/picture?type=square'; }
				return avatarURL;
			}
						
			/******************************************************************************************************
			 * Parse dateStr as formatted date
			 * @return: if dateStr can't be parsed as Date, return dateStr
			 ******************************************************************************************************/
			 
			function formatDate(dateStr){
				var year, month, day, hour, minute, dateUTC, date, ampm, d, time;
				var iso = (dateStr.indexOf(' ')==-1&&dateStr.substr(4,1)=='-'&&dateStr.substr(7,1)=='-'&&dateStr.substr(10,1)=='T') ? true : false;

				if(iso){
					year = dateStr.substr(0,4);
					month = parseInt((dateStr.substr(5,1)=='0') ? dateStr.substr(6,1) : dateStr.substr(5,2))-1;
					day = dateStr.substr(8,2);
					hour = dateStr.substr(11,2);
					minute = dateStr.substr(14,2);
					dateUTC = Date.UTC(year, month, day, hour, minute);
					date = new Date(dateUTC);
				}else{
					d = dateStr.split(' ');
					if(d.length!=6||d[4]!='at')
						return dateStr;
					time = d[5].split(':');
					ampm = time[1].substr(2);
					minute = time[1].substr(0,2);
					hour = parseInt(time[0]);
					if(ampm=='pm')hour+=12;
					date = new Date(d[1]+' '+d[2]+' '+d[3] +' '+ hour+':'+minute);
					date.setTime(date.getTime()-(1000*60*60*7));
				}
				day = (date.getDate()<10)?'0'+date.getDate():date.getDate();
				month = date.getMonth()+1;
				month = (month<10)?'0'+month:month;
				hour = date.getHours();
				minute = (date.getMinutes()<10)?'0'+date.getMinutes():date.getMinutes();
				if(o.timeConversion==12){
					ampm = (hour<12) ? 'am' : 'pm';
					if(hour==0)hour==12;
					else if(hour>12)hour=hour-12;
					if(hour<10)hour='0'+hour;
					return day+'.'+month+'.'+date.getFullYear()+' at '+hour+':'+minute+' '+ampm;
				}
				return day+'.'+month+'.'+date.getFullYear()+' '+o.translateAt+' '+hour+':'+minute;
			}
			
			/******************************************************************************************************
			 * Helper Function
			 ******************************************************************************************************/
			 
			function exists(data){
				if(!data || data==null || data=='undefined' || typeof(data)=='undefined') return false;
				else return true;
			}
			
			function modText(text){
				return cropText(nl2br(autoLink(escapeTags(text))));
			}
			
			function escapeTags(str){
				return str.replace(/</g,'&lt;').replace(/>/g,'&gt;');
			}
			
			function nl2br(str){
				return str.replace(/(\r\n)|(\n\r)|\r|\n/g,"<br />");
			}
			
			function autoLink(str){
				return str.replace(/((http|https|ftp):\/\/[\w?=&.\/-;#~%-]+(?![\w\s?&.\/;#~%"=-]*>))/g, '<a href="$1" target="_blank">$1</a>');
			}

			function cropText(str) {
				var t = str;
				if (str.length>140) {
					t = str.substr(0,140);
					t+="...";
				}
				return t;
			}

			function cropUrl(url) {
				return ' <a href="'+url+'" target="_blank">'+((url.length>30)?url.substr(0,27)+"...":url).replace("http://","")+'</a>';
			}

		});
	};

	/******************************************************************************************************
	 * Defaults 
	 ******************************************************************************************************/
	 
	$.fn.fbWall.defaults = {
		avatarAlternative:		'avatar-alternative.jpg',
		avatarExternal:			'avatar-external.jpg',
		id: 					'Profuturo.GNP',
		max:					5,
		showComments:			true,
		showGuestEntries:		true,
		translateAt:			'at',
		translateLikeThis:		'like this',
		translateLikesThis:		'likes this',
		translateErrorNoData:	'has not shared any information.',
		translatePeople:		'people',
		timeConversion:			24,
		useAvatarAlternative:	false,
		useAvatarExternal:		false,
		accessToken:			'',
		callback: 				null
	};

})(jQuery);
