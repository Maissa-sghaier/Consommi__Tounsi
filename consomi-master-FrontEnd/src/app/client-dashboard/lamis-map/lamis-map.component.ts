import { Component,AfterViewInit } from '@angular/core';
import { Loader } from '@googlemaps/js-api-loader';
import * as L from 'leaflet';

@Component({
  selector: 'app-lamis-map',
  templateUrl: './lamis-map.component.html',
  styleUrls: ['./lamis-map.component.css']
})
export class LamisMapComponent implements AfterViewInit {
  map;

  

  smallIcon = new L.Icon({
    iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-icon.png',
    iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-icon-2x.png',
    iconSize:    [25, 41],
    iconAnchor:  [12, 41],
    popupAnchor: [1, -34],
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    shadowSize:  [41, 41]
  });
  
  
  constructor() { }
  
  ngAfterViewInit(): void {
    this.createMap();
  }

  createMap() {
    const Esprit = {
      lat: 36.8522391, 
      lng: 10.2077839,
    };

    const zoomLevel = 12;

    this.map = L.map('map', {
      center: [Esprit.lat, Esprit.lng],
      zoom: zoomLevel
    });

    const mainLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      minZoom: 12,
      maxZoom: 17,
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    });

    mainLayer.addTo(this.map);
    const descriptionWikipedia = ` The event will happen at 
    Private Higher School of Engineering and Technology (ESPRIT) in ARIANA,TUNISIE`;
    const popupOptions = {
      coords: Esprit,
      text: descriptionWikipedia,
      open: true
    };
    this.addMarker(popupOptions);
  }

  addMarker({coords, text, open}) {
    const marker = L.marker([coords.lat, coords.lng], { icon: this.smallIcon });
    if (open) {
      marker.addTo(this.map).bindPopup(text).openPopup();
    } else {
      marker.addTo(this.map).bindPopup(text);
    }
  }

}