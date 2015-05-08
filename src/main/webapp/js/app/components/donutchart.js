App.DonutChartComponent = Ember.Component.extend({
  tagName: 'svg',

  didInsertElement: function(){
    var chartData = this.get('data');
    var width = 140;
    var height = 85;
    var radius = (Math.min(width, height) / 2);

    var color2;
    var colors = this.get('colors');

    if(colors){
        color2 = d3.scale.ordinal().range(colors.split(","));
    }else {
        color2 = d3.scale.ordinal().range(["#00b4ff", "#ff8153", "#4acab4", "#b2d767", "#ffea88", "#878bb6"]);
    }

    var arc = d3.svg.arc().outerRadius(radius).innerRadius(radius - 20);
    var pie = d3.layout.pie().sort(null).value(function(d) { return d.value; });

    var id = this.$().attr('id');
    var svg = d3.select("#"+id)
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    var g = svg.selectAll(".arc")
        .data(pie(chartData))
        .enter().append("g")
        .attr("class", "arc");

    g.append("path")
        .attr("d", arc)
        .style("fill", function(d, i) { return color2(i); });

    g.append("text")
      .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
      .attr("dy", ".35em")
      .style("text-anchor", "middle")
      .text(function(d) { return d.data.key; });
  }
});
