import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'chat-dialog',
  templateUrl: './chat-dialog.component.html',
  styleUrls: ['./chat-dialog.component.css']
})
export class ChatDialogComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  open(){
    var URL = "http://localhost:8082/springMVC/servlet/?";

    window.open(URL, null);
}
}
