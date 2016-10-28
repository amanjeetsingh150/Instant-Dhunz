from flask import Flask,jsonify
import urllib
import urllib2
import json
import requests
import codecs
from bs4 import BeautifulSoup
app=Flask(__name__)

@app.route('/songlinks/name=<songname>')
def links(songname):
    BASE_URL = 'http://www.youtubeinmp3.com/fetch/?format=JSON&video='
    songname=codecs.encode(songname,'utf-8')
    query=urllib.quote(songname)
    url = "https://www.youtube.com/results?search_query=" + query
    response=urllib2.urlopen(url)
    html=response.read()
    soup=BeautifulSoup(html,"html.parser")
    for v in soup.findAll(attrs={'class':'yt-uix-tile-link'}):
        VIDEO_URL='https://www.youtube.com' + v['href']
        JSON_URL=BASE_URL+VIDEO_URL
        response=urllib.urlopen(JSON_URL)
        try:
            g=[]
            data=json.loads(response.read())
            res=requests.get(JSON_URL)
            Jresponse=res.text
            data=res.json()
            data2=json.loads(Jresponse)
            if 'length' not in data:
                length='No length in data'
            if 'link' not in data:
                link='No link in data'
            if 'title' not in data:
                title='No title in data'
            length=data['length']
            DOWN=data['link']
            title=data['title']
            g.append({'title':title,'length':length,'url':DOWN})
            return jsonify(data) 
        except Exception as e:
            return str(e)
if(__name__ == '__main__'):
	app.run(host="0.0.0.0",port=8000,debug=True,threaded=True)        
    




        


        
    
    
