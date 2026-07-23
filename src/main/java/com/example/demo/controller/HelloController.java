package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String indexHtml() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Java App - System Monitoring & CI/CD Hub</title>
                <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
                <style>
                    * { box-sizing: border-box; margin: 0; padding: 0; }
                    body {
                        font-family: 'Inter', sans-serif;
                        background: #0f172a;
                        color: #f8fafc;
                        min-height: 100vh;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        padding: 40px 20px;
                    }
                    .container {
                        max-width: 900px;
                        width: 100%;
                    }
                    .header {
                        text-align: center;
                        margin-bottom: 40px;
                    }
                    .header h1 {
                        font-size: 2.5rem;
                        background: linear-gradient(135deg, #38bdf8, #818cf8);
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                        margin-bottom: 10px;
                    }
                    .header p {
                        color: #94a3b8;
                        font-size: 1.1rem;
                    }
                    .status-badge {
                        display: inline-block;
                        background: rgba(34, 197, 94, 0.15);
                        color: #4ade80;
                        border: 1px solid rgba(74, 222, 128, 0.3);
                        padding: 6px 16px;
                        border-radius: 9999px;
                        font-size: 0.875rem;
                        font-weight: 600;
                        margin-top: 15px;
                    }
                    .cards {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
                        gap: 20px;
                        margin-bottom: 40px;
                    }
                    .card {
                        background: rgba(30, 41, 59, 0.7);
                        border: 1px solid #334155;
                        border-radius: 16px;
                        padding: 24px;
                        backdrop-filter: blur(10px);
                        transition: transform 0.2s, border-color 0.2s;
                    }
                    .card:hover {
                        transform: translateY(-4px);
                        border-color: #38bdf8;
                    }
                    .card h3 {
                        font-size: 1.25rem;
                        color: #f1f5f9;
                        margin-bottom: 10px;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    }
                    .card p {
                        color: #94a3b8;
                        font-size: 0.9rem;
                        line-height: 1.5;
                        margin-bottom: 20px;
                    }
                    .btn {
                        display: inline-block;
                        width: 100%;
                        text-align: center;
                        background: linear-gradient(135deg, #0284c7, #4f46e5);
                        color: white;
                        padding: 10px 16px;
                        border-radius: 8px;
                        text-decoration: none;
                        font-weight: 600;
                        font-size: 0.9rem;
                        transition: opacity 0.2s;
                    }
                    .btn:hover { opacity: 0.9; }
                    .btn-outline {
                        background: transparent;
                        border: 1px solid #475569;
                        color: #cbd5e1;
                    }
                    .btn-outline:hover {
                        border-color: #f43f5e;
                        color: #f43f5e;
                    }
                    .actions {
                        background: #1e293b;
                        border: 1px solid #334155;
                        border-radius: 16px;
                        padding: 24px;
                    }
                    .actions h2 {
                        font-size: 1.3rem;
                        margin-bottom: 15px;
                        color: #f8fafc;
                    }
                    .action-grid {
                        display: flex;
                        gap: 15px;
                        flex-wrap: wrap;
                    }
                    .action-btn {
                        flex: 1;
                        min-width: 200px;
                        padding: 12px;
                        background: #334155;
                        color: #e2e8f0;
                        border: none;
                        border-radius: 8px;
                        font-weight: 600;
                        cursor: pointer;
                        transition: background 0.2s;
                    }
                    .action-btn:hover { background: #475569; }
                    .action-btn.danger { background: rgba(225, 29, 72, 0.2); color: #fda4af; border: 1px solid #f43f5e; }
                    .action-btn.danger:hover { background: rgba(225, 29, 72, 0.4); }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Java Application Hub</h1>
                        <p>Advanced System Monitoring & CI/CD Pipeline Control Center</p>
                        <span class="status-badge">● System Status: OPERATIONAL</span>
                    </div>

                    <div class="cards">
                        <div class="card">
                            <h3>🚀 REST Application</h3>
                            <p>Spring Boot REST service with CRUD task management & custom Micrometer metrics.</p>
                            <a href="/api/tasks" target="_blank" class="btn">Explore API (/api/tasks)</a>
                        </div>
                        <div class="card">
                            <h3>🔥 Prometheus Engine</h3>
                            <p>Direct view of live task metric graphs scraped by Prometheus engine.</p>
                            <a href="http://localhost:9090/graph?g0.expr=app_tasks_created_total&g0.tab=0" target="_blank" class="btn">Open Prometheus Graph</a>
                        </div>
                        <div class="card">
                            <h3>📈 Grafana Analytics</h3>
                            <p>Direct view of visual dashboards pre-configured for JVM memory & RPS metrics.</p>
                            <a href="http://localhost:3000/d/java-app-metrics/java-spring-boot-application-dashboard?orgId=1" target="_blank" class="btn">Open Grafana Dashboard</a>
                        </div>
                        <div class="card">
                            <h3>⚙️ Jenkins Pipeline</h3>
                            <p>Direct view of your automated CI/CD pipeline job in local Jenkins.</p>
                            <a href="http://localhost:8080/job/java-app-pipeline/" target="_blank" class="btn">Open Jenkins Pipeline</a>
                        </div>
                    </div>

                    <div class="actions">
                        <h2>⚡ Quick Metric Simulator</h2>
                        <div class="action-grid">
                            <button class="action-btn" onclick="fetch('/api/tasks/simulate/work?durationMs=300', {method:'POST'}).then(r=>alert('Workload metric generated! Check Grafana graphs.'))">⚡ Trigger Workload Latency</button>
                            <button class="action-btn danger" onclick="fetch('/api/tasks/simulate/error').then(r=>alert('Simulated 500 error metric! Check Grafana error panel.'))">⚠️ Simulate Error (HTTP 500)</button>
                            <a href="/actuator/prometheus" target="_blank" class="action-btn btn-outline" style="text-align:center; text-decoration:none;">📄 Raw Actuator Metrics</a>
                        </div>
                    </div>
                </div>
            </body>
            </html>
            """;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from Advanced CI/CD Demo Application!");
    }
}
